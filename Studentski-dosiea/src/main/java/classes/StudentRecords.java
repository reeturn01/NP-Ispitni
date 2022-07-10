package classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
            String[] lineSegments = scanner.nextLine().split("\\s+");
            String code = lineSegments[0];
            String field = lineSegments[1];
            List<Integer> grades = new ArrayList<>();
            Arrays.stream(lineSegments)
                    .skip(2)
                    .mapToInt(Integer::parseInt)
                    .forEachOrdered(grades::add);
            studentRecordList.add(new StudentRecord(code, field, grades));
            ++linesRead;
        }
        return linesRead;
    }
}
