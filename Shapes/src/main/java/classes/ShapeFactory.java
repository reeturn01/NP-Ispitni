package classes;

import exceptions.InvalidDimensionException;
import exceptions.InvalidIDException;

public class ShapeFactory {
    public Shape createShape(int type, String id, String... attributes) throws InvalidIDException, InvalidDimensionException {
        if (type == 1){
            double radius = Double.parseDouble(attributes[0]);
            return new Circle(id, radius);
        } else if (type == 2) {
            double side = Double.parseDouble(attributes[0]);
            return new Square(id, side);
        } else if (type == 3) {
            double length = Double.parseDouble(attributes[0]);
            double height = Double.parseDouble(attributes[1]);
            return new Rectangle(id, length, height);
        }else {
            throw new RuntimeException();
        }
    }
}
