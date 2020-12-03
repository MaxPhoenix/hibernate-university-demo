package com.university.demo.controller;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.service.base.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getStudents(@RequestParam(required = false, value = "name") String name){
        if(!StringUtils.isEmpty(name))
            return this.studentService.getStudents(name);
        return this.studentService.getStudents();
    }

    @GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudentById(@PathVariable @NotNull Integer studentId){
        return this.studentService.getStudentById(studentId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO addStudent(@RequestBody StudentDTO student){
        return this.studentService.addStudent(student);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO editStudent(@RequestBody StudentDTO student){
        return this.studentService.editStudent(student);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO deleteStudent(@RequestBody StudentDTO studentDTO){
        return this.studentService.deleteStudent(studentDTO);
    }




}
