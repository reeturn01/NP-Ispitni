package exceptions;

public class DuplicateNumberException extends Exception {
    public DuplicateNumberException() {
    }

    public DuplicateNumberException(String phoneNumber) {
        super(String.format("Duplicate number: [%s]", phoneNumber));
    }
}
