package classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentRecords {

    List<StudentRecord> studentRecordList;
    public StudentRecords() {
        studentRecordList = new ArrayList<>();
    }

    public StudentRecords(List<StudentRecord> studentRecordList) {
        this.studentRecordList = studentRecordList;
    }

    public int readRecords(InputStream inputStream) {
        int linesRead = 0;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()){
            String[] lineSegments = scanner.nextLine().split("\\s+");
            String code = lineSegments[0];
            String field = lineSegments[1];
            List<Integer> grades = Arrays.stream(lineSegments)
                    .skip(2)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            studentRecordList.add(new StudentRecord(code, field, grades));
            ++linesRead;
        }
        scanner.close();
        return linesRead;
    }
}
