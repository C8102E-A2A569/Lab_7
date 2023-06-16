package exceptions;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String elementData, String description) {
        super("Invalid argument passed: " + elementData + (description == null ? "" : ". " + description));
    }
}
