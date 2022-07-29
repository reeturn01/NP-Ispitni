package Unit.tests;

import classes.Department;
import classes.Student;
import classes.StudentRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import other.HelperFunctions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentRecordsShould {
    private StudentRecords studentRecords = new StudentRecords();

    @Mock
    private Department IKImock;
    @Mock
    private Department KNImock;
    @Mock
    private Department MTmock;
    private TreeMap<String, Department> injectedDepartmentMap;

    @BeforeEach
    void setUp() {
        when(MTmock.getDepartmentName()).thenReturn("MT");
        when(KNImock.getDepartmentName()).thenReturn("KNI");
        when(IKImock.getDepartmentName()).thenReturn("IKI");

        injectedDepartmentMap = new TreeMap<>();

        injectedDepartmentMap.put(IKImock.getDepartmentName(), IKImock);
        injectedDepartmentMap.put(MTmock.getDepartmentName(), MTmock);
        injectedDepartmentMap.put(KNImock.getDepartmentName(), KNImock);

    }

    @Test
    void readRecords(){
        String input = HelperFunctions.getSampleInput();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        int numRecordsRead = input.split("\n").length;

        Assertions.assertEquals(0, studentRecords.numberOfStudentRecords());

        Assertions.assertEquals(numRecordsRead, studentRecords.readRecords(inputStream));

        // Verify that records for students where created
        Assertions.assertEquals(numRecordsRead, studentRecords.numberOfStudentRecords());



    }

    @Test
    void numberOfStudentRecords() {
        Map<String, Student> injectedStudentRecordsMap = new HashMap<>();
        injectedStudentRecordsMap.put("AAA", new Student());
        injectedStudentRecordsMap.put("BBB", new Student());
        injectedStudentRecordsMap.put("CCC", new Student());

        studentRecords = new StudentRecords(injectedStudentRecordsMap);

        Assertions.assertEquals(injectedStudentRecordsMap.size(), studentRecords.numberOfStudentRecords());
    }

    @Test
    void writeTable(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        studentRecords = new StudentRecords(injectedDepartmentMap);

        studentRecords.writeTable(outputStream);

        InOrder inOrder = inOrder(IKImock, MTmock, KNImock);

        inOrder.verify(IKImock).writeDepartmentStudentsTo(outputStream);
        inOrder.verify(KNImock).writeDepartmentStudentsTo(outputStream);
        inOrder.verify(MTmock).writeDepartmentStudentsTo(outputStream);

    }

    @Test
    void writeDistributions(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        injectedDepartmentMap = new TreeMap<>();
        when(KNImock.returnCountOfGrade(10)).thenReturn(138);
        when(IKImock.returnCountOfGrade(10)).thenReturn(104);
        when(MTmock.returnCountOfGrade(10)).thenReturn(125);

        injectedDepartmentMap.put(MTmock.getDepartmentName(), MTmock);
        injectedDepartmentMap.put(KNImock.getDepartmentName(), KNImock);
        injectedDepartmentMap.put(IKImock.getDepartmentName(), IKImock);

        studentRecords = new StudentRecords(injectedDepartmentMap);

        studentRecords.writeDistribution(outputStream);

        InOrder inOrder = inOrder(MTmock, IKImock, KNImock);

        inOrder.verify(KNImock).writeDistributions(outputStream);
        inOrder.verify(MTmock).writeDistributions(outputStream);
        inOrder.verify(IKImock).writeDistributions(outputStream);

    }
}
