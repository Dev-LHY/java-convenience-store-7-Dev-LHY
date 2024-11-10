package store.controller;

import java.util.List;
import store.model.Product;
import store.model.Promotion;
import store.model.StockManager;
import store.util.TextFileLoader;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStoreController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final StockManager stockManager = StockManager.getInstance();

    private void initialize() {
        try {
            List<Product> products = Product.createProducts(TextFileLoader.productsLoader());
            List<Promotion> promotions = Promotion.createPromotions(TextFileLoader.promotionsLoader());
            stockManager.addAllProducts(products);
            stockManager.addAllPromotions(promotions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


