package classes;


import main.IHasTimestamp;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

public class GenericCollection<T extends IHasTimestamp & Comparable<T>> {
    private final Map<String, List<T>> categories;

    public GenericCollection() {
        this.categories = new HashMap<>();
    }

    public GenericCollection(Map<String, List<T>> categories) {
        this.categories = categories;
    }

    public void addGenericItem(String category, T element) {
        categories.putIfAbsent(category, new ArrayList<>());
        categories.get(category).add(element);
    }

    public Collection<T> findAllBetween(LocalDateTime from, LocalDateTime end) {
        return categories.values().stream()
                .flatMap(List::stream)
                .filter(t -> t.getTimestamp().isAfter(from) && t.getTimestamp().isBefore(end))
                .sorted(Comparator.reverseOrder())
                .collect(ArrayList::new, (ArrayList<T> ts, T t) -> {
                    if (ts.stream().noneMatch(v -> v.compareTo(t) == 0))
                        ts.add(t);
                }, ArrayList::addAll);
    }

    public Collection<T> itemsFromCategories(List<String> categories) {
        return categories.stream()
                .map(this.categories::get)
                .flatMap(List::stream)
                .sorted(Comparator.reverseOrder())
                .collect(ArrayList::new, (ArrayList<T> ts, T t) -> {
                    if (ts.stream().noneMatch(v -> v.compareTo(t) == 0))
                        ts.add(t);
                }, ArrayList::addAll);
    }

    public Map<String, Set<T>> byMonthAndDay() {
        return categories.values()
                .stream()
                .flatMap(List::stream)
                .sorted(Comparator.reverseOrder())
                .collect(
                        groupingBy(this::getMonthAndDayTimestampFrom, TreeMap::new, toCollection(() -> new TreeSet<T>(Comparator.reverseOrder())))
                );
    }

    public Map<Integer, Long> countByYear() {
        return categories.values()
                .stream()
                .flatMap(List::stream)
                .collect(
                        groupingBy(t -> t.getTimestamp().getYear(), TreeMap::new, counting())
                );
    }

    private String getMonthAndDayTimestampFrom(T element){
        return String.format("%02d-%02d", element.getTimestamp().getMonth().getValue(), element.getTimestamp().getDayOfMonth());
    }
}
