package exceptions;

import java.time.LocalDateTime;

public class DeadlineNotValidException extends Exception {
    public DeadlineNotValidException() {
        super();
    }

    public DeadlineNotValidException(LocalDateTime deadline) {
        super(String.format("The deadline %s has already passed", deadline.toString()));
    }
}
