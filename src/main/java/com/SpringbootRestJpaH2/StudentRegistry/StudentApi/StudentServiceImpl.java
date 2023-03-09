package com.SpringbootRestJpaH2.StudentRegistry.StudentApi;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository repository;

    @Override
    public Student addStudent(Student student) {
        repository.save(student);
        return student;
    }

    @Override
    public List<Student> retrieveAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student retrieveSpecificStudentById(int studentId) {
        Student student = null;
        try {
             student = repository.findById(studentId).orElse(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateStudentById(int studentId, Student student) {
           repository.deleteById(studentId);
           repository.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
       repository.deleteById(studentId);
    }

}
