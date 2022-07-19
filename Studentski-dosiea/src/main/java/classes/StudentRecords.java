package classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRecords {

    List<StudentRecord> studentRecordList;
    public StudentRecords() {
        studentRecordList = new ArrayList<>();
    }

    public int readRecords(InputStream inputStream) {
        int linesRead = 0;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            ++linesRead;
        }
        return linesRead;
    }
}
