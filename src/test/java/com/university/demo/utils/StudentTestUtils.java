package com.university.demo.utils;

import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Student;

public class StudentTestUtils {

    public static Student createStudentFrom(String firstName, String lastName, String email, Integer studentId){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setId(studentId);
        return student;
    }

    public static StudentDTO createStudentDTOFrom(String firstName, String lastName, String email, Integer studentId){
        StudentDTO student = new StudentDTO();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setId(studentId);
        return student;
    }
}
