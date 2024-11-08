package store.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private static final String REGEX = "\\[(.*?)]";

    private final Map<String, Integer> shoppingCart = new LinkedHashMap<>();

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
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
