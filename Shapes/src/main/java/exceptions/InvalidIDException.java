package exceptions;

public class InvalidIDException extends Exception {
    public InvalidIDException() {
        super();
    }

    public InvalidIDException(String id) {
        super(String.format("ID %s is not valid", id));
    }
}
