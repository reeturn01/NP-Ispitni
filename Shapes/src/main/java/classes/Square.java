package classes;

import exceptions.InvalidDimensionException;
import exceptions.InvalidIDException;

public class Square extends Shape {
    private double side;

    public Square(String id, double side) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (!isValidAttribute(side))
            throw new InvalidDimensionException();
        this.side = side;
    }

    @Override
    public void multiplyAllDimensions(double coef) {
        side *= coef;
    }

    @Override
    public double area() {
        return Math.pow(side, 2);
    }

    @Override
    public double perimeter() {
        return 4*side;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f", side, area(), perimeter());
    }
}
