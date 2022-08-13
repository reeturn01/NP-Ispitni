package classes;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Block <T extends Comparable<T>> {
    TreeSet<T> elements;

    public Block() {
        this.elements = new TreeSet<>();
    }

    public Block(TreeSet<T> elements) {
        this.elements = elements;
    }

    public int size() {
        return elements.size();
    }

    public void addElement(T element) {
        elements.add(element);
    }

    public boolean removeElement(T element) {
        return elements.remove(element);
    }

    public TreeSet<T> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return elements.stream()
                .map(T::toString)
                .collect(Collectors.joining(", "));
    }
}
