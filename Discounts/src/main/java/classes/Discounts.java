package classes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;

public class Discounts {

    private Map<String, Store> stores;

    public Discounts() {
        this.stores = new HashMap<>();
    }

    public Discounts(Map<String, Store> stores) {
        this.stores = stores;
    }

    public int readStores(InputStream stream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        List<String[]> lines = bufferedReader.lines()
                .map(s -> s.split("\\s+"))
                .collect(Collectors.toList());

        lines.forEach(strings -> stores.put(
                strings[0], new Store(strings[0], Arrays.stream(strings).skip(1).toArray(String[]::new)))
        );

        return lines.size();
    }

    public List<Store> byAverageDiscount() {
        return stores.values().stream()
                .sorted(
                        comparingDouble(Store::averageDiscount).reversed()
                                .thenComparing(Store::getName)
                )
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        return stores.values().stream()
                .sorted(
                        comparingInt(Store::totalDiscount)
                                .thenComparing(Store::getName)
                )
                .limit(3)
                .collect(Collectors.toList());
    }
}
