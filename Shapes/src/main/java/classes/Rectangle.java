package classes;

import exceptions.InvalidDimensionException;
import exceptions.InvalidIDException;

public class Rectangle extends Shape {
    private double length;
    private double height;

    public Rectangle(String id, double length, double height) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (!isValidAttribute(length, height))
            throw new InvalidDimensionException();
        this.length = length;
        this.height = height;
    }

    @Override
    public double area() {
        return length * height;
    }

    @Override
    public void multiplyAllDimensions(double coef) {
        length *= coef;
        height *= coef;
    }

    @Override
    public double perimeter() {
        return length*2 + height*2;
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f", length, height, area(), perimeter());
    }
}
