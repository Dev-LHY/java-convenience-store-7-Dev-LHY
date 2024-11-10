package store.controller;

import java.util.List;
import java.util.Map;
import store.model.Customer;
import store.model.Product;
import store.model.Promotion;
import store.model.StockManager;
import store.service.ConvenienceStoreService;
import store.util.TextFileLoader;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStoreController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final StockManager stockManager = StockManager.getInstance();

    public void run() {
        initialize();
        while (true) {
            welcome();
            retryIfErrorOccur(this::process);
            if (repurchase()) {
                stockManager.reset();
                return;
            }
        }
    }

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

    private void welcome() {
        outputView.welcome();
        Map<String, List<Product>> stock = stockManager.getStock();
        outputView.stock(stock);
        outputView.inputProductNameAndQuantity();
    }

    private void process() {
        Customer customer = new Customer(inputView.inputProduct());
        ConvenienceStoreService csService = new ConvenienceStoreService(customer);
        csService.checkQuantity();
        csService.validatePromotion();
        outputView.askMembership();
        csService.askMembership(inputView.isY());
        receipt(csService, customer);
        csService.updateStock();
    }

    private void receipt(ConvenienceStoreService csService, Customer customer) {
        outputView.purchaseAmount(customer.getShoppingCart());
        outputView.promotionAmount(csService.getPromotionDiscount());
        outputView.totalAmountInformation(csService.getPurchaseInformation());
        outputView.promotionDiscountAmount(csService.getPromotionDiscountAmount());
        outputView.memberShipDiscount(csService.getMembershipDiscountAmount());
        outputView.actualAmount(csService.getActualAmount());
    }

    private boolean repurchase() {
        outputView.askRepurchase();
        return inputView.isN();
    }

    private void retryIfErrorOccur(Runnable action) {
        while (true) {
            try {
                action.run();
                break;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
