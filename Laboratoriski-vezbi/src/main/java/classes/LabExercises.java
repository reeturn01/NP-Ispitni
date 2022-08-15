package classes;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class LabExercises {
    public static final Comparator<Student> STUDENT_COMPARATOR_ASCENDING = comparingDouble(Student::averagePoints).thenComparing(Student::getIndex);
    private static final Comparator<Student> STUDENT_COMPARATOR_DESCENDING = comparingDouble(Student::averagePoints).reversed().thenComparing(Student::getIndex, reverseOrder());
    private final List<Student> students;

    public LabExercises() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printByAveragePoints(boolean ascending, int n) {
        
        students.stream()
                .sorted(ascending ? STUDENT_COMPARATOR_ASCENDING : STUDENT_COMPARATOR_DESCENDING)
                .limit(n)
                .forEachOrdered(System.out::println);
    }

    public List<Student> failedStudents() {
        return students.stream()
                .filter(Student::hasFailed)
                .sorted(comparingInt(Student::getIndex).thenComparingDouble(Student::averagePoints))
                .collect(toList());
    }

    public Map<Integer, Double> getStatisticsByYear() {
        return students.stream()
                .filter(student -> !student.hasFailed())
                .collect(groupingBy(Student::getStudentYear, TreeMap::new, collectingAndThen(
                        toList(), list -> list.stream()
                                .mapToDouble(Student::averagePoints)
                                .average()
                                .orElse(0.0)
                )));
    }
}
