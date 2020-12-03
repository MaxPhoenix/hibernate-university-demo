package com.university.demo.service.impl;

import com.university.demo.config.exception.exceptions.NotRecordInDataBaseException;
import com.university.demo.dao.StudentDAO;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Student;
import com.university.demo.service.base.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;
    private ModelMapper modelMapper;

    public StudentServiceImpl(@Autowired StudentDAO studentDAO, @Autowired ModelMapper modelMapper) {
        this.studentDAO = studentDAO;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getStudents(){
        List<StudentDTO> students = new ArrayList<>();
        List<Student> retStudents = this.studentDAO.findAll();
        students = retStudents.stream()
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
        return students;
    }

    @Override
    public StudentDTO getStudentById(Integer studentId) {
        Student student = this.studentDAO.findById(studentId).orElseThrow(NotRecordInDataBaseException::new);
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    @Override
    public StudentDTO addStudent(StudentDTO student){
        Student studentToSave = modelMapper.map(student, Student.class);
        Student savedStudent = this.studentDAO.save(studentToSave);
        student = modelMapper.map(savedStudent, StudentDTO.class);
        return student;
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Student studentToSave = modelMapper.map(studentDTO, Student.class);
        boolean exists = this.studentDAO.existsById(studentToSave.getId());
        if(exists) {
            Student savedStudent = this.studentDAO.save(studentToSave);
            studentDTO = modelMapper.map(savedStudent, StudentDTO.class);
        }
        else
            throw new IllegalArgumentException("El estudiante debe existir para ser editado");
        return studentDTO;
    }

    @Override
    public StudentDTO deleteStudent(StudentDTO studentDTO) {
        Student studentToDelete = modelMapper.map(studentDTO, Student.class);
        boolean exists = this.studentDAO.existsById(studentToDelete.getId());
        if(exists)
            this.studentDAO.delete(studentToDelete);
        else
            throw new IllegalArgumentException("El estudiante debe existir para ser eliminado");
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getStudents(String name) {
        List<StudentDTO> students = new ArrayList<>();
        List<Student> retStudents = this.studentDAO.findByFirstNameContainingOrLastNameContaining(name, name);
        students = retStudents.stream()
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
        return students;
    }
}
