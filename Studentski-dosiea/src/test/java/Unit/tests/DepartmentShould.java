package Unit.tests;

import classes.Department;
import classes.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashSet;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentShould {
    private HashSet<Student> injectedStudents;

    @Mock
    Student student1;
    @Mock
    Student student2;
    @Mock
    Student student3;
    private final String departmentName = "KNI";

    @BeforeEach
    void setUp() {
        lenient().when(student1.getAverageGrade()).thenReturn(8.60);
        lenient().when(student1.getCode()).thenReturn("qlacn1");
        lenient().when(student1.returnCountOfGrade(6)).thenReturn(6);
        lenient().when(student1.returnCountOfGrade(7)).thenReturn(7);
        lenient().when(student1.returnCountOfGrade(8)).thenReturn(8);
        lenient().when(student1.returnCountOfGrade(9)).thenReturn(9);
        lenient().when(student1.returnCountOfGrade(10)).thenReturn(10);

        lenient().when(student2.getAverageGrade()).thenReturn(8.45);
        lenient().when(student2.getCode()).thenReturn("fbvux2");
        lenient().when(student2.returnCountOfGrade(6)).thenReturn(6);
        lenient().when(student2.returnCountOfGrade(7)).thenReturn(7);
        lenient().when(student2.returnCountOfGrade(8)).thenReturn(8);
        lenient().when(student2.returnCountOfGrade(9)).thenReturn(9);
        lenient().when(student2.returnCountOfGrade(10)).thenReturn(10);

        lenient().when(student3.getAverageGrade()).thenReturn(8.45);
        lenient().when(student3.getCode()).thenReturn("ugwfy9");
        lenient().when(student3.returnCountOfGrade(6)).thenReturn(6);
        lenient().when(student3.returnCountOfGrade(7)).thenReturn(7);
        lenient().when(student3.returnCountOfGrade(8)).thenReturn(8);
        lenient().when(student3.returnCountOfGrade(9)).thenReturn(9);
        lenient().when(student3.returnCountOfGrade(10)).thenReturn(10);
        injectedStudents = new HashSet<>();
    }

    @Test
    void writeDepartmentStudentsToOutput(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();



        injectedStudents.add(student3);
        injectedStudents.add(student2);
        injectedStudents.add(student1);

        Department department = new Department(departmentName, injectedStudents);

        department.writeDepartmentStudentsTo(outputStream);

        InOrder inOrder = inOrder(student1, student2, student3);

        inOrder.verify(student1).printStudent(outputStream);
        inOrder.verify(student2).printStudent(outputStream);
        inOrder.verify(student3).printStudent(outputStream);

        String expected = String.format("%s%n", departmentName);
        Assertions.assertEquals(expected, outputStream.toString());
    }

    @Test
    void writeDistributions(){
        injectedStudents.add(student1);
        injectedStudents.add(student2);
        injectedStudents.add(student3);
        Department department = new Department(departmentName, injectedStudents);
//[оценка со 2 места порамнети во десно] | [по еден знак * на секои 10 оценки] ([вкупно оценки])
        String expectedOutput =
                String.format("%2d | **(%d)%n", 6,18)+
                String.format("%2d | ***(%d)%n", 7,21)+
                String.format("%2d | ***(%d)%n",8 , 24)+
                String.format("%2d | ***(%d)%n", 9, 27)+
                String.format("%2d | ***(%d)%n", 10, 30);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        department.writeDistributions(outputStream);

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void returnNumberOfTens(){
        injectedStudents.add(student2);
        injectedStudents.add(student3);
        injectedStudents.add(student1);

        Department department = new Department("depName", injectedStudents);

        Assertions.assertEquals(30 ,department.returnCountOfGrade(10));

        injectedStudents.forEach(
                student -> verify(student).returnCountOfGrade(10)
        );
    }
}
