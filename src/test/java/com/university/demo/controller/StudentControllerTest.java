package com.university.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.service.base.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should Get All Students When Making GET Request to endpoint - /api/student ")
    public void shouldListAllStudents() throws Exception{
        StudentDTO studentDTO = of("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO studentDTO2 = of("Fede", "Lencina", "flencina@gmail.com", 2);

        Mockito.when(studentService.getStudents()).thenReturn(Arrays.asList(studentDTO, studentDTO2));

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(get("/api/student/"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Max")))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Lencina")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].firstName", Matchers.is("Fede")))
                .andExpect(jsonPath("$[1].lastName", Matchers.is("Lencina")));
    }

    @Test
    @DisplayName("Should Get All Students When Making GET Request to endpoint - /api/student?name='Chris' ")
    public void shouldListAllStudentsByName() throws Exception{
        StudentDTO studentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);
        StudentDTO studentDTO2 = of("Chris", "Edwards", "cedwards@gmail.com", 2);


        Mockito.when(studentService.getStudents("Chris")).thenReturn(Arrays.asList(studentDTO, studentDTO2));

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(get("/api/student?name=Chris"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Pratt")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].lastName", Matchers.is("Edwards")));

    }

    @Test
    @DisplayName("Should Get Student With id 1 When Making GET Request to endpoint - /api/student/1 ")
    public void shouldGetStudentByIdOne() throws Exception{
        StudentDTO studentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);

        Mockito.when(studentService.getStudentById(1)).thenReturn(studentDTO);

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(get("/api/student/1"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.firstName", Matchers.is("Chris")))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("cpratt@gmail.com")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Pratt")));

    }

    @Test
    @DisplayName("Should Add Student When Making POST Request to endpoint - /api/student/ ")
    public void shouldAddStudent() throws Exception{
        StudentDTO studentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);
        StudentDTO returnedStudentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);

        Mockito.when(studentService.addStudent(studentDTO)).thenReturn(returnedStudentDTO);

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(post("/api/student")
                        .content(asJsonString(studentDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.firstName", Matchers.is("Chris")))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("cpratt@gmail.com")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Pratt")));

    }

    @Test
    @DisplayName("Should Edit Student When Making PUT Request to endpoint - /api/student/ ")
    public void shouldEditStudent() throws Exception{
        StudentDTO studentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);
        StudentDTO returnedStudentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);

        Mockito.when(studentService.editStudent(studentDTO)).thenReturn(returnedStudentDTO);

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(put("/api/student")
                        .content(asJsonString(studentDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.firstName", Matchers.is("Chris")))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("cpratt@gmail.com")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Pratt")));

    }

    @Test
    @DisplayName("Should Delete Student When Making DELETE Request to endpoint - /api/student/ ")
    public void shouldDeleteStudent() throws Exception{
        StudentDTO studentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);
        StudentDTO returnedStudentDTO = of("Chris", "Pratt", "cpratt@gmail.com", 1);

        Mockito.when(studentService.deleteStudent(studentDTO)).thenReturn(returnedStudentDTO);

        //get method from MockMvcRequestBuilders
        //status, content and jsonPath from MockMvcResultMatchers
        mockMvc.perform(delete("/api/student")
                        .content(asJsonString(studentDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.firstName", Matchers.is("Chris")))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("cpratt@gmail.com")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Pratt")));

    }

    private static StudentDTO of(String firstName, String lastName, String email, Integer id){
        StudentDTO student = new StudentDTO();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setId(id);
        return student;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
