package store.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private static final String REGEX = "\\[(.*?)]";
    private static final String DELIMITER_DASH = "-";
    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static final int PROMOTION_BENEFIT = 1;

    private final Map<String, Integer> shoppingCart = new LinkedHashMap<>();

    public Customer(String items) {
        putItemsToShoppingCart(items);
    }

    public void addQuantity(String name) {
        shoppingCart.put(name, shoppingCart.get(name) + PROMOTION_BENEFIT);
    }

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    private void putItemsToShoppingCart(String items) {
        List<String> parseItems = parseItems(items);
        for (String item : parseItems) {
            String[] splitItem = item.split(DELIMITER_DASH);
            String name = splitItem[NAME_INDEX];
            int quantity = Integer.parseInt(splitItem[QUANTITY_INDEX]);
            shoppingCart.put(name, quantity);
        }
    }

    private List<String> parseItems(String items) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(items);
        List<String> parseItems = new ArrayList<>();
        while (matcher.find()) {
            parseItems.add(matcher.group(1));
        }
        return parseItems;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
