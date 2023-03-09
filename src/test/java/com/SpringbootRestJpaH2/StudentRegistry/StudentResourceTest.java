package com.SpringbootRestJpaH2.StudentRegistry;

import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.Student;
import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.StudentResource;
import com.SpringbootRestJpaH2.StudentRegistry.StudentApi.StudentServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = StudentResource.class)
public class StudentResourceTest {

    @MockBean
    private StudentServiceImpl studentService;

    @Autowired
    private MockMvc mockMvc;

    private static String SPECIFIC_QUESION_URL = "http://localhost:8080/students/1";

    @Test
    void retriveSpecificStudentById() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESION_URL).
                accept(MediaType.APPLICATION_JSON);

        Student student = new Student(1,"Anuj","Pune","CS");

        when(studentService.retrieveSpecificStudentById(1)).thenReturn(student);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

         System.out.println(mvcResult.getResponse().getStatus());
    }

}
