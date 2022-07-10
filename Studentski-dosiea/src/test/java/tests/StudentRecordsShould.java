package tests;

import classes.StudentRecord;
import classes.StudentRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentRecordsShould {
    public static Stream<Arguments> factoryForWriteTable() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(
                        Arrays.asList(getStudentRecordMockWith("sneft1", "KNI", "9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6"), getStudentRecordMockWith("zriou9", "PET", "8 7 8 6 9 6 8 7 9")), "55")
                )
                .build();
    }

    private static StudentRecord getStudentRecordMockWith(String code, String field, String grades) {
        Double average = Arrays.stream(grades.split("\\s+")).mapToDouble(Double::parseDouble).average().orElse(0);
        StudentRecord studentRecordMock = Mockito.mock(StudentRecord.class);
        Mockito.when(studentRecordMock.getCode())
                .thenReturn(code);
        Mockito.when(studentRecordMock.getMeanAverageGrade())
                .thenReturn(average);
        return studentRecordMock;
    }

    @ParameterizedTest
    @CsvSource({
            "'sneft1 KNI 9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6', '1'",
            "'zriou9 PET 8 7 8 6 9 6 8 7 9\r\ncfsxo2 IKI 7 7 9 6 9 9 7 8 8 8 8 7 10 9 8 10 9 9', 2",
            "'', 0"
    })
    void read_and_store_data_from_inputStream_and_return_num_records_read_for_readRecords(String inputData, int functionReturn){
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        StudentRecords studentRecords = new StudentRecords();

        Assertions.assertEquals(functionReturn, studentRecords.readRecords(inputStream));
    }

    @ParameterizedTest(name = "[{index}]")
    @MethodSource("factoryForWriteTable")
    void print_records_grouped_by_field_then_sortedBy_gradeAverage_in_descendingOrder_roundedTo_2_decimal_for_writeTable(List<StudentRecord> studentRecordList, String expected){
        Assertions.assertEquals(0, studentRecordList.size());
    }
}
