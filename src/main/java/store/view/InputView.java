package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.exception.ExceptionMessage;

public class InputView {
    public String inputProduct() {
        return Console.readLine();
    }

    public boolean isN() {
        String input = Console.readLine();
        if (input.equals("N")) {
            return true;
        }
        if (input.equals("Y")) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    public boolean isY() {
        String input = Console.readLine();
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

}
