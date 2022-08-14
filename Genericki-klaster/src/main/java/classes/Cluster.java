package classes;

import java.util.*;
import java.util.stream.Collectors;

public class Cluster <T extends Point2D>{
    private Map<Long, T> elements;

    public Cluster() {
        this.elements = new HashMap<>();
    }

    public Cluster(Map<Long, T> elements) {
        this.elements = elements;
    }

    public void addItem(T element){
        elements.put(element.getId(), element);
    }
    public void near(long id, int top){
        T element1 = elements.remove(id);
        List<AbstractMap.SimpleEntry<Long, Double>> elementsToPrint = elements.values().stream()
                .map(t -> new AbstractMap.SimpleEntry<Long, Double>(t.getId(), this.euclidianDistance(element1, t)))
                .sorted(Map.Entry.comparingByValue())
                .limit(top)
                .collect(Collectors.toList());



        for (int i = 0; i < elementsToPrint.size(); i++) {
            System.out.printf("%d. %d -> %.3f%n", i+1, elementsToPrint.get(i).getKey(), elementsToPrint.get(i).getValue());
        }
    }

    private double euclidianDistance(T e1, T e2){
        double xCoords = Math.pow(e1.getX()-e2.getX(),2);
        double yCoords = Math.pow(e1.getY()-e2.getY(),2);
        return Math.sqrt(xCoords+yCoords);
    }
}
