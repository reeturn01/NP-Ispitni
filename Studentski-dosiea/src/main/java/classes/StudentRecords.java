package classes;

import classes.exceptions.NotImplementedException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class StudentRecords {
    private final Map<String, Student> studentRecordsMap;
    private final Map<String, Department> departmentMap;

    public StudentRecords(Map<String, Student> injectedStudentRecordsMap) {
        this.studentRecordsMap = injectedStudentRecordsMap;
        this.departmentMap = new TreeMap<>();
    }
    public StudentRecords(TreeMap<String, Department> injectedDepartmentMap){
        this.studentRecordsMap = new HashMap<>();
        this.departmentMap = injectedDepartmentMap;
    }
    public StudentRecords() {
        this.studentRecordsMap = new HashMap<>();
        this.departmentMap = new TreeMap<>();
    }

    public int readRecords(InputStream inputStream) {
        int numRecordsRead = 0;
        try(Scanner scanner = new Scanner(inputStream)){
            while (scanner.hasNextLine()){
                String studentCode = scanner.next();
                String studentDepartment = scanner.next();
                List<Integer> studentGrades = new ArrayList<>();
                while (scanner.hasNextInt()){
                    studentGrades.add(scanner.nextInt());
                }
                studentRecordsMap.put(studentCode, new Student(studentCode, studentDepartment, studentGrades));
                departmentMap.putIfAbsent(studentDepartment, new Department(studentDepartment));
                departmentMap.get(studentDepartment).addStudent(new Student(studentCode, studentDepartment, studentGrades));
                numRecordsRead++;
            }
        }
        return numRecordsRead;
    }

    public void writeTable(OutputStream out) {
        departmentMap.values().forEach(department ->
                department.writeDepartmentStudentsTo(out));
    }

    public void writeDistribution(OutputStream out) {
        departmentMap.values().stream()
                .sorted(Comparator.comparingInt((Department d) -> d.returnCountOfGrade(10)).reversed())
                .forEachOrdered(department -> department.writeDistributions(out));
    }

    public int numberOfStudentRecords() {
        return studentRecordsMap.size();
    }
}
