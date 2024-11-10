package store.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import store.model.Product;
import store.model.ProductPrice;

public class OutputView {
    private final DecimalFormat formatter = new DecimalFormat("###,###");

    public void welcome() {
        System.out.println(OutputMessage.WELCOME.getMessage());
    }

    public void inputProductNameAndQuantity() {
        System.out.println(OutputMessage.INPUT_PRODUCT_NAME_AND_QUANTITY.getMessage());
    }

    public void stock(Map<String, List<Product>> stock) {
        for (Map.Entry<String, List<Product>> stockEntry : stock.entrySet()) {
            List<Product> products = stockEntry.getValue();
            printStock(products);
        }
        System.out.print(System.lineSeparator());
    }

    private void printStock(List<Product> products) {
        for (Product product : products) {
            String quantity = Integer.toString(product.getQuantity()) + "개";
            if (quantity.equals("0개")) {
                quantity = "재고 없음";
            }
            String promotion = product.getPromotion();
            if (promotion == null) {
                promotion = "";
            }
            System.out.println(
                    "- " + product.getName() + " " + formatter.format(product.getPrice()) + "원 " + quantity + " "
                            + promotion);
        }
    }

    public void askMembership() {
        System.out.println(OutputMessage.ASK_MEMBERSHIP.getMessage());
    }

    public void askRepurchase() {
        System.out.println(OutputMessage.ASK_REPURCHASE.getMessage());
    }

    public void promotionNotApplySomething(String name, int number) {
        System.out.println(OutputMessage.PROMOTION_NOT_APPLY_SOMETHING.getFormattedMessage(name, number));
    }

    public void addPromotionProduct(String name, int number) {
        System.out.println(OutputMessage.ADD_PROMOTION_PRODUCT.getFormattedMessage(name, number));
    }

    public void purchaseAmount(Map<String, Integer> shoppingCart) {
        System.out.println("===============W 편의점===============");
        System.out.printf(leftAlign("상품명", 22) + leftAlign("수량", 10) + leftAlign("금액", 10) + "\n");
        for (Entry<String, Integer> shoppingCartEntry : shoppingCart.entrySet()) {
            String productName = shoppingCartEntry.getKey();
            int quantity = shoppingCartEntry.getValue();
            int price = shoppingCartEntry.getValue() * ProductPrice.getPriceByName(productName);
            System.out.printf(leftAlign(productName, 22) + leftAlign(Integer.toString(quantity), 10) + rightAlign(
                    formatter.format(price), 1) + "\n");
        }
    }

    public void promotionAmount(Map<String, Integer> promotionDiscount) {
        System.out.println("===============증\t 정===============");
        for (Entry<String, Integer> promotionDiscountEntry : promotionDiscount.entrySet()) {
            String productName = promotionDiscountEntry.getKey();
            int quantity = promotionDiscountEntry.getValue();
            System.out.printf(leftAlign(productName, 22) + leftAlign(Integer.toString(quantity), 10) + "\n");
        }
    }

    public void totalAmountInformation(List<Integer> purchaseInformation) {
        System.out.println("======================================");
        System.out.printf(leftAlign("총구매액", 22) +
                leftAlign(Integer.toString(purchaseInformation.get(0)), 10) +
                rightAlign(formatter.format(purchaseInformation.get(1)), 1) + "\n");
    }

    public void promotionDiscountAmount(int amount) {
        System.out.printf(leftAlign("행사할인", 32) + "-" + rightAlign(formatter.format(amount), 1) + "\n");
    }

    public void memberShipDiscount(int amount) {
        System.out.printf(leftAlign("멤버십할인", 32) + "-" + rightAlign(formatter.format(amount), 1) + "\n");
    }

    public void actualAmount(int amount) {
        System.out.printf(leftAlign("내실돈", 33) + rightAlign(formatter.format(amount), 1) + "\n");
    }

    private static int getKorCnt(String kor) {
        int cnt = 0;
        for (int i = 0; i < kor.length(); i++) {
            char ch = kor.charAt(i);
            if (ch >= '\uAC00' && ch <= '\uD7A3') {
                cnt++;
            }
        }
        return cnt;
    }

    public static String leftAlign(String word, int size) {
        int korCnt = getKorCnt(word);
        int totalLength = word.length() + korCnt;

        StringBuilder sb = new StringBuilder(word);
        for (int i = totalLength; i < size; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String rightAlign(String word, int size) {
        int korCnt = getKorCnt(word);
        int totalLength = word.length() + korCnt;

        StringBuilder sb = new StringBuilder();
        for (int i = totalLength; i < size; i++) {
            sb.append(' ');
        }
        sb.append(word);
        return sb.toString();
    }
}
