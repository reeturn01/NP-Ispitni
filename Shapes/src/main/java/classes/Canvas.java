package classes;

import exceptions.InvalidDimensionException;
import exceptions.InvalidIDException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Canvas {
    public static final Comparator<Shape> SHAPE_COMPARATOR = comparingDouble(Shape::area);
    private final TreeSet<Shape> shapes;
    private final ShapeFactory shapeFactory;
    public Canvas(){
        this.shapes = new TreeSet<>(SHAPE_COMPARATOR);
        this.shapeFactory = new ShapeFactory();
    }


    public void readShapes(InputStream in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()){
            int typeOfShape = scanner.nextInt();
            String id = scanner.next();
            String[] shapeAttributes = scanner.nextLine().trim().split("\\s+");
            try {
                Shape newShape = shapeFactory.createShape(typeOfShape, id, shapeAttributes);
                shapes.add(newShape);
            }
            catch (InvalidIDException e){
                System.out.println(e.getMessage());
            } catch (InvalidDimensionException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void printAllShapes(PrintStream out) {
        shapes.forEach(out::println);
    }

    public void scaleShapes(String userID, double coef) {
        shapes.stream()
                .filter(shape -> shape.getId().equals(userID))
                .forEach(shape -> shape.multiplyAllDimensions(coef));
    }

    public void printByUserId(PrintStream out) {
        Map <String, TreeSet<Shape>> shapesToPrint = shapes.stream()
                .collect(
                        groupingBy(Shape::getId, toCollection(() -> new TreeSet<>(comparingDouble(Shape::perimeter))))
                );
        List<String> sortedKeys = shapesToPrint.keySet()
                .stream()
                .sorted(
                        comparingInt(key -> shapesToPrint.get(key).size()).reversed()
                                .thenComparingDouble(key -> shapesToPrint.get(key).stream().collect(summingDouble(Shape::area)))
                )
                .collect(toList());

        sortedKeys.forEach(key -> {
            out.printf("Shapes of user: %s%n", key);
            shapesToPrint.get(key).forEach(out::println);
        });
    }

    public void statistics(PrintStream out) {
        DoubleSummaryStatistics statistics = shapes.stream()
                .collect(summarizingDouble(Shape::area));
        out.printf("count: %d%n", statistics.getCount());
        out.printf("sum: %.2f%n", statistics.getSum());
        out.printf("min: %.2f%n", statistics.getMin());
        out.printf("average: %.2f%n", statistics.getAverage());
        out.printf("max: %.2f%n", statistics.getMax());
    }

}
