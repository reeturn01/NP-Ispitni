package classes;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Department {
    public static final Comparator<Student> STUDENT_COMPARATOR = Comparator.comparing(Student::getAverageGrade).reversed().thenComparing(Student::getCode);
    String departmentName;
    private final TreeSet<Student> students;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.students = new TreeSet<>(STUDENT_COMPARATOR);
    }
    public Department(String departmentName, Set<Student> students){
        this.departmentName = departmentName;
        this.students = students.stream().collect(Collectors.toCollection(() -> new TreeSet<>(STUDENT_COMPARATOR)));

    }

    public String getDepartmentName() {
        return departmentName;
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public void writeDepartmentStudentsTo(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(departmentName);
        students.forEach(student -> student.printStudent(out));

    }

    public void writeDistributions(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        for (int i = 6; i < 11; i++) {
            int countOfGradeI = returnCountOfGrade(i);
            int numberOfStars = roundNumberToTens(countOfGradeI)/10;
            String stars = "*".repeat(numberOfStars);
            printWriter.printf("%2d | %s(%d)%n", i, stars, countOfGradeI);
        }
    }

    private int roundNumberToTens(int number) {
        if (number%10 == 0)
            return number;
        return number+(10-number%10);
    }

    public int returnCountOfGrade(int grade) {
        return students.stream()
                .map(student -> student.returnCountOfGrade(grade))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
