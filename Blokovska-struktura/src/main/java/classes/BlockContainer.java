package classes;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BlockContainer<T extends Comparable<T>>{
    private int maxNumElements;
    private Stack<Block<T>> container;

    public BlockContainer(int maxNumElements) {
        this.maxNumElements = maxNumElements;
        this.container = new Stack<>();
    }
    public void add(T a){
        if (container.empty())
            container.push(new Block<>());
        if (container.peek().size() == maxNumElements)
            container.push(new Block<>());
        container.peek().addElement(a);
    }

    public boolean remove(T a){
        if (!container.peek().removeElement(a)) {
            return false;
        }
        if (container.peek().size() == 0)
            container.pop();
        return true;
    }

    public void sort(){
        List<T> sortedElements = container.stream()
                .flatMap(tBlock -> tBlock.getElements().stream())
                .sorted()
                .collect(Collectors.toList());

        Stack<Block<T>> tmpContainer = new Stack<>();
        sortedElements.forEach(element ->{
            if (tmpContainer.isEmpty())
                tmpContainer.push(new Block<>());
            if (tmpContainer.peek().size() == maxNumElements)
                tmpContainer.push(new Block<>());
            tmpContainer.peek().addElement(element);
        });
        container = tmpContainer;
    }

    @Override
    public String toString() {
        return container.stream()
                .map(tBlock -> String.format("[%s]", tBlock.toString()))
                .collect(Collectors.joining(","));
    }
}
