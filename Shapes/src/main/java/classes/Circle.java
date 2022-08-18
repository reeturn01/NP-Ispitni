package classes;

import exceptions.InvalidDimensionException;
import exceptions.InvalidIDException;

public class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (!isValidAttribute(radius))
            throw new InvalidDimensionException();
        this.radius = radius;
    }

    @Override
    public void multiplyAllDimensions(double coef) {
        radius *= coef;
    }

    @Override
    public double area() {
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public double perimeter() {
        return 2*radius*Math.PI;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f", radius, area(), perimeter());
    }
}
