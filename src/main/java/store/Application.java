package store;

import store.controller.ConvenienceStoreController;

public class Application {
    public static void main(String[] args) {
        ConvenienceStoreController csController = new ConvenienceStoreController();
        csController.run();
    }
}
