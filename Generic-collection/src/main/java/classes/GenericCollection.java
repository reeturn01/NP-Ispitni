package classes;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericCollection<T> {
    public void addGenericItem(String category, T integerElement) {
        throw new RuntimeException();
    }

    public Collection<T> itemsFromCategories(List<String> categories) {
        throw new RuntimeException();
    }

    public Collection<T> findAllBetween(LocalDateTime start, LocalDateTime end) {
        throw new RuntimeException();
    }

    public Map<String, Set<T>> byMonthAndDay() {
        throw new RuntimeException();
    }

    public Map<Integer, Long> countByYear() {
        throw new RuntimeException();
    }
}
