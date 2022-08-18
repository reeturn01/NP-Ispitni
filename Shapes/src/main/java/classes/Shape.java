package classes;

import exceptions.InvalidIDException;

import java.util.Arrays;

public abstract class Shape {
    private final String id;
    public Shape(String id) throws InvalidIDException {
        if (!isValidId(id))
            throw new InvalidIDException(id);
        this.id = id;
    }

    private boolean isValidId(String id) {
        if (id.length() == 6)
            return id.chars().allMatch(Character::isLetterOrDigit);
        return false;
    }

    boolean isValidAttribute(double... attributes){
        return Arrays.stream(attributes)
                .noneMatch(v -> v == 0.0);
    }

    public String getId() {
        return id;
    }

    public abstract void multiplyAllDimensions(double coef);

    public abstract double area();

    public abstract double perimeter();
}
