package com.SpringbootRestJpaH2.StudentRegistry.StudentApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentResource {

    @Autowired
    private StudentServiceImpl serviceImpl;

    @RequestMapping(value = "/students",method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student student1 = null;
        try {
            student1 =  serviceImpl.addStudent(student);
            return ResponseEntity.of(Optional.of(student1));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping("/students")
    public ResponseEntity<List<Student>> retriveAllStuddents() {

         List<Student> students = serviceImpl.retrieveAllStudents();
         if(students.size()<=0)
         {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
         return ResponseEntity.of(Optional.of(students));
    }

    @RequestMapping(value="/students/{studentId}",method = RequestMethod.GET)
    public ResponseEntity<Student> retriveSpecificStudentById(@PathVariable int studentId) {

        Student student = serviceImpl.retrieveSpecificStudentById(studentId);
        if(student==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(student));
    }

    @RequestMapping(value="/students/{studentId}",method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudentById(@PathVariable int studentId , @RequestBody Student student) {
        try {
            serviceImpl.updateStudentById(studentId,student);
            return ResponseEntity.ok().body(student);
        }
       catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }

    }

    @RequestMapping(value = "/students/{studentId}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudentById(@PathVariable int studentId) {
        try {
            serviceImpl.deleteStudentById(studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }












}
