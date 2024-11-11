package store.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private static final String DELIMITER_COMMA = ",";
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int PROMOTION_INDEX = 3;

    private final String name;
    private final int price;
    private final String promotion;
    private int quantity;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = setPromotion(promotion);
    }

    public static List<Product> createProducts(List<String> textLines) {
        List<Product> products = new ArrayList<>();
        for (String textLine : textLines) {
            products.add(parseProduct(textLine));
        }
        return products;
    }

    private static Product parseProduct(String textLine) {
        String[] fields = textLine.split(DELIMITER_COMMA);
        String name = fields[NAME_INDEX];
        int price = Integer.parseInt(fields[PRICE_INDEX]);
        int quantity = Integer.parseInt(fields[QUANTITY_INDEX]);
        String promotion = fields[PROMOTION_INDEX];
        return new Product(name, price, quantity, promotion);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotion() {
        return promotion;
    }

    public int getQuantity() {
        return quantity;
    }

    private String setPromotion(String promotion) {
        if ("null".equals(promotion)) {
            return null;
        }
        return promotion;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", promotion='" + promotion + '\'' +
                '}';
    }
}
