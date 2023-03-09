package com.SpringbootRestJpaH2.StudentRegistry.StudentApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentDetailsCommandLineRunner implements CommandLineRunner {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new Student(1,"Anuj","Pune","CS"));
//        repository.save(new Student(2,"Santosh","Pune","CS"));
    }
}
