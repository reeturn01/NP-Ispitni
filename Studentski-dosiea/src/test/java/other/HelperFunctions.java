package other;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HelperFunctions {
    public static final Path SAMPLE_OUTPUT_PATH = Path.of("src/test/resources/SampleOutput.txt");
    public static final Path SAMPLE_INPUT_PATH = Path.of("src/test/resources/SampleInput.txt");
    public static final Path WRITE_TABLE_OUTPUT_DATA_PATH = Path.of("src/test/resources/writeTableOutputData.txt");
    //    public static Stream<Arguments> factoryForWriteTable() {
//        return Stream.<Arguments>builder()
//                .add(Arguments.of(
//                        Arrays.asList(getStudentRecordMockWith("sneft1", "KNI", "9 10 8 8 10 9 6 7 7 6 9 9 7 7 10 9 6 10 10 8 6 7 9 7 9 7 6"), getStudentRecordMockWith("zriou9", "PET", "8 7 8 6 9 6 8 7 9")), "KNI\r\nsneft1 8.00\r\nPET\r\nzriou9 7.56")
//                ).add(Arguments.of(
//                        Arrays.asList(getStudentRecordMockWith("kijez3", "PET", "6 9 6 8 9 7 6 6"), getStudentRecordMockWith("qlacn1", "IKI", "7 10 9 9 8"), getStudentRecordMockWith("fbvux2", "IKI", "6 9 10 7 8 10 9 8 10 7 9"), getStudentRecordMockWith("ugwfy9", "IKI", "9 9 8 7 8 10 9 6 9 10 8")), """
//                                IKI\r
//                                qlacn1 8.60\r
//                                fbvux2 8.45\r
//                                ugwfy9 8.45\r
//                                PET\r
//                                kijez3 7.13"""
//                ))
//                .build();
//    }
//
//    public static StudentRecord getStudentRecordMockWith(String code, String field, String grades) {
//        double average = Arrays.stream(grades.split("\\s+"))
//                .mapToDouble(Double::parseDouble)
//                .average()
//                .orElse(0);
//        average = BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        //average = Double.parseDouble(String.format("%.2f", average));
//        StudentRecord studentRecordMock = mock(StudentRecord.class);
//        when(studentRecordMock.getCode())
//                .thenReturn(code);
//        when(studentRecordMock.getField())
//                .thenReturn(field);
//        when(studentRecordMock.getMeanAverageGrade())
//                .thenReturn(average);
//        return studentRecordMock;
//    }
//
//    static List<String> readLinesFromFile(String pathname){
//        try {
//            return Files.readAllLines(Paths.get(pathname));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static List<StudentRecord> getMockedListOfStudentRecordFromStudentInputsFile(){
//        List<String> fileLines = readLinesFromFile("src\\test\\resources\\SampleInput.txt");
//        List<StudentRecord> toReturnList = new ArrayList<>();
//        for(String line : fileLines){
//            String[] lineTokens = line.split("\\s+");
//
//            String code = lineTokens[0];
//            String field = lineTokens[1];
//            double averageMeanGradeForStudent = Arrays.stream(lineTokens).skip(2)
//                    .mapToDouble(Double::parseDouble)
//                    .average()
//                    .orElse(0);
//
//            List<Integer> grades = Arrays.stream(lineTokens).skip(2)
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//            StudentRecord mockStudent = mock(StudentRecord.class);
//            when(mockStudent.getCode()).thenReturn(code);
//            when(mockStudent.getField()).thenReturn(field);
//            when(mockStudent.getMeanAverageGrade()).thenReturn(averageMeanGradeForStudent);
//            when(mockStudent.getGrades()).thenReturn(grades);
//            toReturnList.add(mockStudent);
//        }
//        return toReturnList;
//    }

    public static String getSampleOutput() {
        try {
            return Files.readString(SAMPLE_OUTPUT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSampleInput() {
        try {
            return Files.readString(SAMPLE_INPUT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getSampleInputLines() {
        try {
            return Files.readAllLines(SAMPLE_INPUT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readWriteTableOuputData() {
        try {
            return Files.readString(WRITE_TABLE_OUTPUT_DATA_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
