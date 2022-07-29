package classes;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Student{
    private String code;

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    private String field;
    private List<Integer> grades;

    public Student(String code, String studentDepartment, List<Integer> grades) {

        this.code = code;
        this.field = studentDepartment;
        this.grades = grades;
    }

    public Student() {
        this.code = "";
        this.field = "";
        this.grades = new ArrayList<>();
    }

    public double getAverageGrade() {
        double average = grades.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);

        BigDecimal bigDecimal = new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }

    public void printStudent(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.printf("%s %.2f%n", code, getAverageGrade());

    }

    public int returnCountOfGrade(int grade) {
        return (int) grades.stream()
                .mapToInt(Integer::intValue)
                .filter(g -> g==10)
                .count();
    }
}
