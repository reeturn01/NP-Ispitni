package Unit.tests;

import classes.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentShould {
    @ParameterizedTest
    @CsvFileSource(resources = "/printStudentData.csv", delimiter = '|')
    void printStudent(String studentCode, String studentField, String gradesString){
        List<Integer> grades = Arrays.stream(gradesString.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Student student = new Student(studentCode, studentField, grades);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        student.printStudent(outputStream);

        String expected = String.format("%s %.2f%n", studentCode, student.getAverageGrade());
        Assertions.assertEquals(expected, outputStream.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6",
            "8 7 8 6 9 6 8 7 9",
            "7 7 9 6 9 9 7 8 8 8 8 7 10 9 8 10 9 9"
    })
    void getAverageGrade(String gradesString){
        List<Integer> grades = Arrays.stream(gradesString.split("\\s+"))
                                        .mapToInt(Integer::parseInt)
                                        .boxed()
                                        .collect(Collectors.toList());
        double expectedAverage = grades.stream()
                                        .mapToDouble(Integer::doubleValue)
                                        .average()
                                        .orElse(0);

        Student student = new Student("", "", grades);

        Assertions.assertEquals(expectedAverage, student.getAverageGrade());
    }
    @ParameterizedTest
    @CsvSource({
            "9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6, 6, 5",
            "8 7 8 6 9 6 8 7 9, 7, 2",
            "9 9 9 9 9 9 9 9 9, 8, 0",
            "9 8 7 6 5 4 3 2 1, 9, 1",
            "10 10 10 10 10, 10, 5"
    })
    void returnCountOfGrade(String gradesString, int grade, int expectedOutput){
        List<Integer> grades = Arrays.stream(gradesString.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Student student = new Student("", "", grades);

        Assertions.assertEquals(expectedOutput, student.returnCountOfGrade(grade));
    }
}
