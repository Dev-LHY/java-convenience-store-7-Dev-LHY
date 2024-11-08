package store.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {
    private final Map<String, Integer> shoppingCart = new LinkedHashMap<>();

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
