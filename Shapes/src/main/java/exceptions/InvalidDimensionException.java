package exceptions;

public class InvalidDimensionException extends Exception {
    public InvalidDimensionException() {
        super("Dimension 0 is not allowed!");
    }

    public InvalidDimensionException(String message) {
        super(message);
    }
}
