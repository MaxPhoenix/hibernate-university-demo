package com.university.demo.service.base;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getStudents();

    StudentDTO getStudentById(@NotNull Integer studentId);

    StudentDTO addStudent(StudentDTO student);

    StudentDTO editStudent(StudentDTO studentDTO);

    StudentDTO deleteStudent(StudentDTO studentDTO);

    List<StudentDTO> getStudents(String name);
}
