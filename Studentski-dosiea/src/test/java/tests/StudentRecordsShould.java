package tests;

import classes.StudentRecord;
import classes.StudentRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class StudentRecordsShould {

    private StudentRecords studentRecords;


    public static Stream<Arguments> factoryForWriteTable() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(
                        Arrays.asList(getStudentRecordMockWith("sneft1", "KNI", "9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6"), getStudentRecordMockWith("zriou9", "PET", "8 7 8 6 9 6 8 7 9")), "KNI\r\nsneft1 8.00\r\nPET\r\nzriou9 7.56")
                ).add(Arguments.of(
                        Arrays.asList(getStudentRecordMockWith("kijez3", "PET", "6 9 6 8 9 7 6 6"), getStudentRecordMockWith("qlacn1", "IKI", "7 10 9 9 8"), getStudentRecordMockWith("fbvux2", "IKI", "6 9 10 7 8 10 9 8 10 7 9"), getStudentRecordMockWith("ugwfy9", "IKI", "9 9 8 7 8 10 9 6 9 10 8")),"IKI\r\nqlacn1 8.60\r\n" +
                                "fbvux2 8.45\r\n" +
                                "ugwfy9 8.45\r\n" +
                                "PET\r\nkijez3 7.13"
                ))
                .build();
    }

    private static StudentRecord getStudentRecordMockWith(String code, String field, String grades) {
        Double average = Arrays.stream(grades.split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .average()
                .orElse(0);
        average = BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //average = Double.parseDouble(String.format("%.2f", average));
        StudentRecord studentRecordMock = mock(StudentRecord.class);
        when(studentRecordMock.getCode())
                .thenReturn(code);
        when(studentRecordMock.getField())
                .thenReturn(field);
        when(studentRecordMock.getMeanAverageGrade())
                .thenReturn(average);
        return studentRecordMock;
    }

    @BeforeEach
    void setUp(){
        studentRecords = new StudentRecords();
    }

    @ParameterizedTest
    @CsvSource({
            "'sneft1 KNI 9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6', '1'",
            "'zriou9 PET 8 7 8 6 9 6 8 7 9\r\ncfsxo2 IKI 7 7 9 6 9 9 7 8 8 8 8 7 10 9 8 10 9 9', 2",
            "'', 0"
    })
    void read_and_store_data_from_inputStream_and_return_num_records_read_for_readRecords(String inputData, int functionReturn){
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        List<StudentRecord> studentRecordList = mock(List.class);


        studentRecords = new StudentRecords(studentRecordList);
        Assertions.assertEquals(functionReturn, studentRecords.readRecords(inputStream));
        verify(studentRecordList, times(functionReturn)).add(any());
    }

    @ParameterizedTest(name = "[{index}]")
    @MethodSource("factoryForWriteTable")
    void print_records_grouped_by_field_then_sortedBy_gradeAverage_in_descendingOrder_roundedTo_2_decimal_for_writeTable(List<StudentRecord> studentRecordList, String expected){
        OutputStream argumentOutputStream = new ByteArrayOutputStream();
        studentRecords = new StudentRecords(studentRecordList);

        studentRecords.writeTable(argumentOutputStream);
        Assertions.assertEquals(expected, argumentOutputStream.toString().trim());
    }
}
