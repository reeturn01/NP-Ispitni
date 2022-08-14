package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class Store {
    private final String name;
    private List<DiscountArticle> articles;

    public Store(String name, String[] articleDiscounts) {
        this.name = name;
        this.articles = new ArrayList<>();
        Arrays.stream(articleDiscounts)
                .map(s -> s.split(":"))
                .forEach(strings -> articles.add(new DiscountArticle(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]))));
    }

    public int totalDiscount(){
        return articles.stream()
                .mapToInt(DiscountArticle::getDiscountAmount)
                .sum();
    }
    public double averageDiscount(){
        return articles.stream()
                .mapToInt(DiscountArticle::discountPercent)
                .sum() / (double)articles.size();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name).append(System.lineSeparator());
        sb.append(String.format("Average discount: %.1f%%%n", averageDiscount()));
        sb.append(String.format("Total discount: %d%n", totalDiscount()));

        String articlesAsString = articles.stream()
                .sorted(
                        comparingInt(DiscountArticle::discountPercent).reversed()
                                .thenComparing(comparingInt(DiscountArticle::getDiscountAmount).reversed())
                )
                .map(DiscountArticle::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        return sb.append(articlesAsString).toString();
    }
}
