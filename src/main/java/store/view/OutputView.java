package store.view;

public class OutputView {
    public void welcome() {
        System.out.println(OutputMessage.WELCOME.getMessage());
    }

    public void inputProductNameAndQuantity() {
        System.out.println(OutputMessage.INPUT_PRODUCT_NAME_AND_QUANTITY.getMessage());
    }

    public void askMembership() {
        System.out.println(OutputMessage.ASK_MEMBERSHIP.getMessage());
    }

    public void askRepurchase() {
        System.out.println(OutputMessage.ASK_REPURCHASE.getMessage());
    }

    public void promotionNotApplySomething(String name, int number) {
        System.out.println(OutputMessage.PROMOTION_NOT_APPLY_SOMETHING.getFormattedMessage(name, number));
    }

    public void addPromotionProduct(String name, int number) {
        System.out.println(OutputMessage.ADD_PROMOTION_PRODUCT.getFormattedMessage(name, number));
    }
}
