package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord {
    final String code;
    String field;

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    List<Integer> grades;

    public StudentRecord() {
        this.code = "";
        this.field = "";
        this.grades = new ArrayList<>();
    }

    public StudentRecord(String code, String field, List<Integer> grades) {
        this.code = code;
        this.field = field;
        this.grades = grades;
    }

    public double getMeanAverageGrade() {
        double average = grades.stream().mapToDouble(Double::valueOf).average().getAsDouble();
        return Double.parseDouble(BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP).toString());
    }
}
