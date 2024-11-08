package store.service;

import java.util.List;
import store.model.Customer;
import store.model.Promotion;
import store.model.StockManager;
import store.util.TextFileLoader;

public class ConvenienceStoreService {
    private final List<Promotion> promotions;
    private final StockManager stock;
    private final Customer customer;

    public ConvenienceStoreService(Customer customer) {
        this.promotions = Promotion.createPromotions(TextFileLoader.promotionsLoader());
        this.stock = StockManager.getInstance();
        this.customer = customer;
    }
}
