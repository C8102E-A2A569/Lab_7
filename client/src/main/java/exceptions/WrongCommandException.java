package exceptions;

import java.util.Arrays;

public class WrongCommandException extends Exception {
    public WrongCommandException(String command, String[] arguments) {
        super("There's no '" + command + "' call with arguments: " + Arrays.toString(arguments));
    }
}
