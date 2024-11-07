package store.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private static final StockManager stockManager = new StockManager();
    private final Map<String, List<Product>> stock = new LinkedHashMap<>();

    private StockManager() {
    }

    public static StockManager getInstance() {
        return stockManager;
    }

    public Map<String, List<Product>> getStock() {
        return stock;
    }

    private void addProduct(Product product) {
        String key = product.getName();
        List<Product> classifiedProducts = stock.computeIfAbsent(key, k -> new ArrayList<>());
        classifiedProducts.add(product);
    }
}
