package classes;

import java.util.List;
import java.util.Objects;

public class Student {
    public static final int MINIMUM_NUMBER_OF_APPEARANCES_TO_NOT_FAIL = 8;
    private final int index;
    private final int studentYear;
    private final List<Integer> points;

    private Student() {
        throw new RuntimeException();
    }

    public Student(String index, List<Integer> points) {
        if (index.chars().allMatch(Character::isDigit) && points.size()<=10) {
            this.index = Integer.parseInt(index);
            this.studentYear = 10-Character.digit(index.charAt(1), 10);
            this.points = points;
        }
        else {
            throw new RuntimeException();
        }
    }
    public double averagePoints(){
        return points.stream()
                .mapToInt(Integer::intValue)
                .sum() / 10.0;
    }
    public int getIndex() {
        return index;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public boolean hasFailed(){
        return points.size()< MINIMUM_NUMBER_OF_APPEARANCES_TO_NOT_FAIL;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return index == student.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f", index, hasFailed() ? "NO": "YES", averagePoints());
    }
}
