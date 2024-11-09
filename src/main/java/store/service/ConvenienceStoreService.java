package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import store.exception.ExceptionMessage;
import store.model.Customer;
import store.model.Product;
import store.model.ProductPrice;
import store.model.Promotion;
import store.model.StockManager;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStoreService {
    private final StockManager stockManager;
    private final Customer customer;
    private final Map<String, Integer> shoppingCart;
    private final Map<String, Promotion> promotions;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Map<String, Integer> promotionDiscount = new LinkedHashMap<>();
    private int membershipDiscount;

    public ConvenienceStoreService(Customer customer) {
        this.stockManager = StockManager.getInstance();
        this.customer = customer;
        this.shoppingCart = customer.getShoppingCart();
        this.promotions = stockManager.getPromotions();
    }

    public List<Integer> getPurchaseInformation() {
        List<Integer> purchaseInformation = new ArrayList<>();
        int totalQuantity = 0;
        int totalAmount = 0;
        for (Entry<String, Integer> shoppingCartEntry : shoppingCart.entrySet()) {
            totalQuantity += shoppingCartEntry.getValue();
            totalAmount += ProductPrice.getPriceByName(shoppingCartEntry.getKey()) * shoppingCartEntry.getValue();
        }
        purchaseInformation.add(totalQuantity);
        purchaseInformation.add(totalAmount);
        return purchaseInformation;
    }

    public int getPromotionDiscountAmount() {
        int totalAmount = 0;
        Map<String, Integer> promotionDiscount = getPromotionDiscount();
        for (Map.Entry<String, Integer> promotionDiscountEntry : promotionDiscount.entrySet()) {
            totalAmount += ProductPrice.getPriceByName(promotionDiscountEntry.getKey()) * promotionDiscountEntry.getValue();
        }
        return totalAmount;
    }

    public void askMembership(boolean isMembership) {
        if (isMembership) {
            List<Integer> totalAmount = getPurchaseInformation();
            int promotionDiscountAmount = getPromotionDiscountAmount();
            this.membershipDiscount =  (int) ((totalAmount.get(1) - promotionDiscountAmount) * 0.3);
        }
        if (!isMembership) {
            this.membershipDiscount = 0;
        }
    }

    public int getMembershipDiscountAmount() {
        if (membershipDiscount > 8000) {
            this.membershipDiscount = 8000;
        }
        return membershipDiscount;
    }

    public int getActualAmount() {
        List<Integer> totalAmount = getPurchaseInformation();
        return totalAmount.get(1) - getMembershipDiscountAmount() - getPromotionDiscountAmount();
    }

    public Map<String, Integer> getPromotionDiscount() {
        return promotionDiscount;
    }

    public void checkQuantity() {
        for (Map.Entry<String, Integer> shoppingCartEntry : shoppingCart.entrySet()) {
            int totalQuantityByName = stockManager.getTotalQuantityByName(shoppingCartEntry.getKey());
            int shoppingCartQuantity = shoppingCartEntry.getValue();
            if (totalQuantityByName < shoppingCartQuantity) {
                throw new IllegalArgumentException(ExceptionMessage.EXCEED_QUANTITY.getMessage());
            }
        }
    }

    public void validatePromotion() {
        Map<String, List<Product>> stock = stockManager.getStock();
        String promotionName = null;
        for (Map.Entry<String, Integer> shoppingCart : shoppingCart.entrySet()) {
            List<Product> products = stock.get(shoppingCart.getKey());
            Promotion promotion = promotions.get(products.getFirst().getPromotion());
            if (checkPromotionTime(promotion.getStart_date(), promotion.getEnd_date())) {
                continue;
            }
            validatePromotions(shoppingCart, products, promotionName);
        }
    }

    private void validatePromotions(Entry<String, Integer> shoppingCart, List<Product> products, String promotionName) {
        int promotionQuantity = stockManager.getPromotionQuantityByName(shoppingCart.getKey());
        plusQuantity(shoppingCart, promotionQuantity, products);
        int normalQuantity = promotionQuantity - shoppingCart.getValue();
        promotionName = getPromotionName(promotionName, products);
        int divide = getDivide(promotionName);
        getRemainder(shoppingCart, normalQuantity, promotionQuantity, divide);
    }

    private boolean checkPromotionTime(LocalDate startDate, LocalDate endDate) {
        LocalDate todayDate = DateTimes.now().toLocalDate();
        return todayDate.isBefore(startDate) || todayDate.isAfter(endDate);
    }

    private void plusQuantity(Entry<String, Integer> shoppingCart, int promotionQuantity, List<Product> products) {
        if (promotionQuantity > shoppingCart.getValue()) {
            int divide = getDivide(getPromotionName(null, products));
            int result = promotionQuantity % divide;
            askPlusQuantity(shoppingCart, result, divide);
        }
    }

    private void askPlusQuantity(Entry<String, Integer> shoppingCart, int result, int divide) {
        if (result == divide - 1 && shoppingCart.getValue() % divide != 0) {
            outputView.addPromotionProduct(shoppingCart.getKey(), 1);
            if (inputView.isY()) {
                customer.addQuantity(shoppingCart.getKey());
            }
        }
    }

    private static String getPromotionName(String promotionName, List<Product> products) {
        if (products.getFirst().getPromotion() != null) {
            promotionName = products.getFirst().getPromotion();
        }
        return promotionName;
    }

    private int getDivide(String promotionName) {
        Promotion promotion;
        int divide = 0;
        if (promotionName != null) {
            promotion = promotions.get(promotionName);
            int buy = promotion.getBuy();
            int get = promotion.getGet();
            divide = buy + get;
        }
        return divide;
    }

    private void getRemainder(Entry<String, Integer> shoppingCart, int normalQuantity, int promotionQuantity,
                              int divide) {
        int remainder = setRemainder(shoppingCart, promotionQuantity, normalQuantity, divide);
        if (remainder != 0) {
            outputView.promotionNotApplySomething(shoppingCart.getKey(), remainder);
            if (inputView.isN()) {
                customer.reduceQuantity(shoppingCart.getKey(), remainder);
            }
        }
    }

    private int setRemainder(Entry<String, Integer> shoppingCart, int promotionQuantity, int normalQuantity,
                             int divide) {
        if (normalQuantity >= 0) {
            promotionDiscount.put(shoppingCart.getKey(), shoppingCart.getValue() / divide);
            return shoppingCart.getValue() % divide;
        }
        promotionDiscount.put(shoppingCart.getKey(), promotionQuantity / divide);
        int i = promotionQuantity % divide;
        return (normalQuantity * -1) + i;
    }
}
