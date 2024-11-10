package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import store.exception.ExceptionMessage;

public class InputView {
    public String inputProduct() {
        return Console.readLine();
    }

    public boolean isN() {
        return retryIfErrorOccur(this::isNProcess);
    }

    public boolean isY() {
        return retryIfErrorOccur(this::isYProcess);
    }

    private boolean isNProcess() {
        String input = Console.readLine();
        if (input.equals("N")) {
            return true;
        }
        if (input.equals("Y")) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    private boolean isYProcess() {
        String input = Console.readLine();
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    private <T> T retryIfErrorOccur(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
