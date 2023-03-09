package com.SpringbootRestJpaH2.StudentRegistry.StudentApi;

import java.util.List;

public interface StudentService {

    public Student addStudent(Student student);

    public List<Student> retrieveAllStudents();

    public Student retrieveSpecificStudentById(int studentId);

    public void updateStudentById(int studentId,Student student);

    public void deleteStudentById(int studentId);


}
