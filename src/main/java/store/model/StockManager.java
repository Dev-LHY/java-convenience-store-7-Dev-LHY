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
}
