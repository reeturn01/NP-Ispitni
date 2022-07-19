package tests;

import classes.StudentRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StudentRecordShould {
    @ParameterizedTest
    @CsvSource({
            "'qlacn1', 'IKI', '7 10 9 9 8', 8.60",
            "'fbvux2', 'IKI', '6 9 10 7 8 10 9 8 10 7 9', 8.45",
            "'ugwfy9', 'IKI', '9 9 8 7 8 10 9 6 9 10 8', 8.45",
            "'cscbh7', 'IKI', '10 6 10 7 10 8 9 6 8 7 6 9 6 7 9 7 9 9 8 7 8 6 8 9 7 8 8 8 8 9 8 10 9 10', 8.06"
    })
    void return_mean_average_grade(String code, String field, String grades, double expectedOutput){
        StudentRecord record = new StudentRecord(code, field, Arrays.stream(grades.trim().split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));

        Assertions.assertEquals(expectedOutput, record.getMeanAverageGrade());
    }
}
