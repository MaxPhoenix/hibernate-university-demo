package com.university.demo.service;

import com.university.demo.dao.StudentDAO;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.Student;
import com.university.demo.service.base.StudentService;
import com.university.demo.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentDAO studentDAO ;
    @Mock
    private ModelMapper modelMapper ;

    private StudentService studentService;

    @BeforeEach
    void setUp(){
        this.studentService = new StudentServiceImpl(studentDAO, modelMapper);
    }

    @Test
    @DisplayName("Get Student by id test")
    void getStudentByIdTest(){
        Student mockStudent = this.getMockStudent();
        StudentDTO mockStudentDTO = this.getMockStudentDto();

        Mockito.when(this.studentDAO.findById(1)).thenReturn(Optional.of(mockStudent));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);

        StudentDTO studentDTO = studentService.getStudentById(1);
        Assertions.assertEquals(studentDTO, mockStudentDTO);
    }

    @Test
    @DisplayName("Save Student test ")
    void saveStudentTest(){
        Student mockStudentToSave = this.getMockStudentToSave();
        StudentDTO mockStudentDtoToSave = this.getMockStudentDtoToSave();
        Student mockSavedStudent = this.getMockStudent();
        StudentDTO mockSavedStudentDTO = this.getMockStudentDto();

        Mockito.when(this.modelMapper.map(mockStudentDtoToSave, Student.class)).thenReturn(mockStudentToSave);
        Mockito.when(this.studentDAO.save(mockStudentToSave)).thenReturn(mockSavedStudent);
        Mockito.when(this.modelMapper.map(mockSavedStudent, StudentDTO.class)).thenReturn(mockSavedStudentDTO);

        StudentDTO savedStudentDTO = studentService.addStudent(mockStudentDtoToSave);

        Mockito.verify(studentDAO, Mockito.times(1)).save(ArgumentMatchers.any(Student.class));
        Assertions.assertEquals(savedStudentDTO, mockSavedStudentDTO);
    }

    public Student getMockStudent(){
        Student student = new Student();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        student.setId(1);
        return student;
    }

    public StudentDTO getMockStudentDto(){
        StudentDTO student = new StudentDTO();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        student.setId(1);
        return student;
    }

    private StudentDTO getMockStudentDtoToSave(){
        StudentDTO student = new StudentDTO();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        return student;
    }

    public Student getMockStudentToSave(){
        Student student = new Student();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        return student;
    }



}
