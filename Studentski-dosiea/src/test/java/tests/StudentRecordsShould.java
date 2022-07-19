package tests;

import classes.StudentRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StudentRecordsShould {
    @ParameterizedTest
    @CsvSource({
            "sneft1 KNI 9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6, 1",
            "'zriou9 PET 8 7 8 6 9 6 8 7 9\r\ncfsxo2 IKI 7 7 9 6 9 9 7 8 8 8 8 7 10 9 8 10 9 9', 2",
            "'', 0"
    })
    void read_and_store_data_from_inputStream_and_return_num_records_read_for_readRecords(String inputData, int functionReturn){
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        StudentRecords studentRecords = new StudentRecords();

        Assertions.assertEquals(functionReturn, studentRecords.readRecords(inputStream));
    }

    @Test
    void
}
