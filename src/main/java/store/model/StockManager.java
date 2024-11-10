package store.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.exception.ExceptionMessage;

public class StockManager {
    private static final StockManager stockManager = new StockManager();
    private static final int OUT_OF_STOCK = 0;
    private final Map<String, List<Product>> stock = new LinkedHashMap<>();
    private final Map<String, Promotion> promotions = new LinkedHashMap<>();

    private StockManager() {
    }

    public static StockManager getInstance() {
        return stockManager;
    }

    public Map<String, List<Product>> getStock() {
        return stock;
    }

    public Map<String, Promotion> getPromotions() {
        return promotions;
    }

    public void addAllPromotions(List<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            this.promotions.put(promotion.getName(), promotion);
        }
    }

    public void updateQuantity(Map.Entry<String, Integer> shoppingCartEntry) {
        List<Product> products = stock.get(shoppingCartEntry.getKey());
        int shoppingCartQuantity = shoppingCartEntry.getValue();
        reduceQuantity(products, shoppingCartQuantity);
    }

    private void reduceQuantity(List<Product> products, int shoppingCartQuantity) {
        for (Product product : products) {
            int productQuantity = product.getQuantity();
            if (productQuantity >= shoppingCartQuantity) {
                product.setQuantity(product.getQuantity() - productQuantity);
                break;
            }
            shoppingCartQuantity -= product.getQuantity();
            product.setQuantity(OUT_OF_STOCK);
        }
    }

    public int getTotalQuantityByName(String name) {
        int totalQuantity = 0;
        try {
            List<Product> products = stock.get(name);
            for (Product product : products) {
                totalQuantity += product.getQuantity();
            }
        } catch (NullPointerException e) {
            System.out.println(ExceptionMessage.INVALID_INPUT.getMessage());
        }

        return totalQuantity;
    }

    public int getPromotionQuantityByName(String name) {
        return getQuantityByNameAndPromotionStatus(name, true);
    }

    public int getNonePromotionQuantityByName(String name) {
        return getQuantityByNameAndPromotionStatus(name, false);
    }

    private int getQuantityByNameAndPromotionStatus(String name, boolean checkPromotion) {
        List<Product> products = stock.get(name);
        for (Product product : products) {
            boolean hasPromotion = product.getPromotion() != null;
            if (checkPromotion == hasPromotion) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    public void addAllProducts(List<Product> products) {
        for (Product product : products) {
            addProduct(product);
        }

        for (Map.Entry<String, List<Product>> stockEntry : stock.entrySet()) {
            addNullPromotionProductIfRequired(stockEntry.getValue());
        }
    }

    private void addProduct(Product product) {
        String key = product.getName();
        List<Product> classifiedProducts = stock.computeIfAbsent(key, k -> new ArrayList<>());
        classifiedProducts.add(product);
    }

    private void addNullPromotionProductIfRequired(List<Product> classifiedProducts) {
        boolean hasOneProduct = classifiedProducts.size() == 1;
        boolean hasPromotion = classifiedProducts.getFirst().getPromotion() != null;

        if (hasOneProduct && hasPromotion) {
            String name = classifiedProducts.getFirst().getName();
            int price = classifiedProducts.getFirst().getPrice();
            int quantity = 0;
            String promotion = null;
            classifiedProducts.add(new Product(name, price, quantity, promotion));
        }
    }

    public void reset() {
        stock.clear();
        promotions.clear();
    }

    @Override
    public String toString() {
        return "StockManager{" +
                "stock=" + stock +
                '}';
    }
}
