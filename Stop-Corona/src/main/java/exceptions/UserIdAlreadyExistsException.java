package exceptions;

public class UserIdAlreadyExistsException extends Exception {
    public UserIdAlreadyExistsException(String id) {
        super(String.format("User with id: %s already exists!", id));
    }
}
