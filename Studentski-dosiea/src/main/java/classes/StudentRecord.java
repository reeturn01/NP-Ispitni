package classes;

import java.util.ArrayList;
import java.util.List;

public class StudentRecord {
    final String code;
    String field;
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
}
