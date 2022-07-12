package classes;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
                    .collect(toList());
            studentRecordList.add(new StudentRecord(code, field, grades));
            ++linesRead;
        }
        scanner.close();
        return linesRead;
    }

    public void writeTable(OutputStream outputStream) {
//        Map<String, List<StudentRecord>>outputMap = studentRecordList.stream().collect(groupingBy(
//                StudentRecord::getField, collectingAndThen(
//                        toList(), e-> e.stream()
//                                .sorted(Comparator.comparingDouble(StudentRecord::getMeanAverageGrade).thenComparing(StudentRecord::getCode))
//                                .collect(toList())
//                )));
        Map<String, List<String>> groupedRecords = studentRecordList.stream()
                .collect(groupingBy(StudentRecord::getField, collectingAndThen(
                        toList(), records -> records.stream()
                                .sorted(Comparator.comparingDouble(StudentRecord::getMeanAverageGrade).reversed()
                                        .thenComparing(StudentRecord::getCode))
                                .map(record -> record.getCode()+" " + String.format("%.2f", record.getMeanAverageGrade()))
                                .collect(toList())
                )));

        try (PrintWriter printWriter = new PrintWriter(outputStream)){
            for (String key : groupedRecords.keySet()){
                printWriter.println(key);
                groupedRecords.get(key).stream()
                        .forEachOrdered(printWriter::println);
            }
        }
//        PrintWriter pw = new PrintWriter(outputStream);
//        outputMap.keySet().stream().forEachOrdered(k -> {
//            pw.println(k);
//            outputMap.get(k).stream().forEachOrdered(e-> pw.println(e.getCode()+" "+e.getMeanAverageGrade()));
//        });
    }
}
