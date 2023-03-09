package com.SpringbootRestJpaH2.StudentRegistry;

import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.Student;
import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.StudentRepository;
import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.ArgumentCaptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    static
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Captor
    ArgumentCaptor<Student> addStudentCaptor = null;

    @Captor
    ArgumentCaptor<Integer> findByIdCaptor = null;

    @Test
    public void AddStudentTest() {
        Student student = new Student(1,"Anuj","Pune","CS");
        when(studentRepository.save(student)).thenReturn(student);
        Assertions.assertEquals(student,studentService.addStudent(student));
        verify(studentRepository,atLeastOnce()).save(student);
    }

    @Test
    public void AddStudentArgumentCaptor() {
        Student student = new Student(1,"Anuj","Pune","CS");
        studentService.addStudent(student);
        verify(studentRepository).save(addStudentCaptor.capture());
        Student student1 = addStudentCaptor.getValue();
        assertThat(student1).isEqualTo(student);
    }

    @Test
    public void retrieveSpecificStudentByIdReturnStudentObject() {
        Student student = new Student(1,"Anuj","Pune","CS");
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Assertions.assertEquals(student,studentService.retrieveSpecificStudentById(1));
        verify(studentRepository,atLeastOnce()).findById(1);
    }

    @Test
    public void ArgumentCaptorForretrieveSpecificStudentByIdReturnStudentObject() {
        Student student = new Student(1,"Anuj","Pune","CS");
        studentService.retrieveSpecificStudentById(1);
        verify(studentRepository).findById(findByIdCaptor.capture());
        int studentId  = findByIdCaptor.getValue();
        assertThat(studentId).isEqualTo(1);
    }

    @Test
    public void retriveAllStuddentsReturnsListOfStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Anuj","Pune","CS"));
        students.add(new Student(2,"Santosh","Pune","CS"));
        when(studentRepository.findAll()).thenReturn(students);
        Assertions.assertEquals(students,studentService.retrieveAllStudents());
        verify(studentRepository,atLeastOnce()).findAll();
    }

    @Test
    public void deleteStudentById() {
        studentService.deleteStudentById(1);
        verify(studentRepository,atLeastOnce()).deleteById(1);
    }

    @Test
    public void updateStudentById() {
        Student student = new Student(1,"Anuj","Pune","CS");
        studentService.updateStudentById(1,student);
        verify(studentRepository,atLeastOnce()).deleteById(1);
        verify(studentRepository,atLeastOnce()).save(student);
    }

    @Test
    public void updateStudentByIdArgumentCaptor(){
        Student student = new Student(1,"Anuj","Pune","CS");
        studentService.updateStudentById(1,student);
        verify(studentRepository).deleteById(findByIdCaptor.capture());
        int studentId  = findByIdCaptor.getValue();
        assertThat(studentId).isEqualTo(1);
        verify(studentRepository).save(addStudentCaptor.capture());
        Student student1 = addStudentCaptor.getValue();
        assertThat(student1).isEqualTo(student);
    }
}
