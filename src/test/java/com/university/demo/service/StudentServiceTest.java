package com.university.demo.service;

import com.university.demo.dao.StudentDAO;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.Student;
import com.university.demo.service.base.StudentService;
import com.university.demo.service.impl.StudentServiceImpl;
import com.university.demo.utils.StudentTestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.studentDAO.findById(1)).thenReturn(Optional.of(mockStudent));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);

        StudentDTO studentDTO = studentService.getStudentById(1);
        Assertions.assertEquals(studentDTO, mockStudentDTO);
    }

    @Test
    @DisplayName("Save Student test ")
    void saveStudentTest(){
        Student mockStudentToSave = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", null);
        StudentDTO mockStudentDtoToSave = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", null);

        Student mockSavedStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockSavedStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.modelMapper.map(mockStudentDtoToSave, Student.class)).thenReturn(mockStudentToSave);
        Mockito.when(this.studentDAO.save(mockStudentToSave)).thenReturn(mockSavedStudent);
        Mockito.when(this.modelMapper.map(mockSavedStudent, StudentDTO.class)).thenReturn(mockSavedStudentDTO);

        StudentDTO savedStudentDTO = studentService.addStudent(mockStudentDtoToSave);

        Mockito.verify(studentDAO, Mockito.times(1)).save(ArgumentMatchers.any(Student.class));
        Assertions.assertEquals(savedStudentDTO, mockSavedStudentDTO);
    }

    @Test
    @DisplayName("Should edit Student test")
    void shouldEditStudentTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@gmail.com", 1);
        StudentDTO editedMockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "maxlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        mockStudentDTO.setEmail("maxlencina@gmail.com");

        Mockito.when(this.modelMapper.map(mockStudentDTO, Student.class)).thenReturn(mockStudent);
        Mockito.when(this.studentDAO.existsById(1)).thenReturn(true);
        Mockito.when(this.studentDAO.save(mockStudent)).thenReturn(mockStudent);
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);

        StudentDTO studentDTO = studentService.editStudent(mockStudentDTO);
        Mockito.verify(studentDAO, Mockito.times(1)).save(ArgumentMatchers.any(Student.class));
        Assertions.assertEquals(studentDTO, editedMockStudentDTO);
    }

    @Test
    @DisplayName("Should not edit Student and throw IllegalArgumentException test")
    void shouldNotEditStudentTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        mockStudentDTO.setEmail("maxlencina@gmail.com");

        Mockito.when(this.modelMapper.map(mockStudentDTO, Student.class)).thenReturn(mockStudent);
        Mockito.when(this.studentDAO.existsById(1)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.editStudent(mockStudentDTO));
        Mockito.verify(studentDAO, Mockito.times(0)).save(ArgumentMatchers.any(Student.class));
    }

    @Test
    @DisplayName("Should delete Student test")
    void shouldDeleteStudentTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.modelMapper.map(mockStudentDTO, Student.class)).thenReturn(mockStudent);
        Mockito.when(this.studentDAO.existsById(1)).thenReturn(true);

        StudentDTO studentDTO = studentService.deleteStudent(mockStudentDTO);
        Mockito.verify(studentDAO, Mockito.times(1)).delete(ArgumentMatchers.any(Student.class));
        Assertions.assertEquals(studentDTO, mockStudentDTO);
    }

    @Test
    @DisplayName("Should not delete Student and throw IllegalArgumentException test")
    void shouldNOtDeleteStudentTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.modelMapper.map(mockStudentDTO, Student.class)).thenReturn(mockStudent);
        Mockito.when(this.studentDAO.existsById(1)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.deleteStudent(mockStudentDTO));
        Mockito.verify(studentDAO, Mockito.times(0)).delete(ArgumentMatchers.any(Student.class));
    }

    @Test
    @DisplayName("Get Students test")
    void getStudentsTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Student mockStudent2 = StudentTestUtils.createStudentFrom("Fede", "Lencina", "flencina@gmail.com", 2);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO2 = StudentTestUtils.createStudentDTOFrom("Fede", "Lencina", "flencina@gmail.com", 2);

        Mockito.when(this.studentDAO.findAll()).thenReturn(Arrays.asList(mockStudent, mockStudent2));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);
        Mockito.when(this.modelMapper.map(mockStudent2, StudentDTO.class)).thenReturn(mockStudentDTO2);

        List<StudentDTO> studentDTO = studentService.getStudents();

        Assertions.assertAll(
                () -> Assertions.assertEquals(studentDTO.size(), 2),
                () -> Assertions.assertEquals(studentDTO.get(0), mockStudentDTO),
                () -> Assertions.assertEquals(studentDTO.get(1), mockStudentDTO2)
        );
    }

    @Test
    @DisplayName("Get Students By Name Containing 'Max' test")
    void getStudentsByNameContainingMaxTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.studentDAO.findByFirstNameContainingOrLastNameContaining("Max" , "Max")).thenReturn(Collections.singletonList(mockStudent));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);

        List<StudentDTO> studentDTOByNameMax = studentService.getStudents("Max");

        Assertions.assertAll(
                () -> Assertions.assertEquals(studentDTOByNameMax.size(), 1),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(0), mockStudentDTO)
        );
    }

    @Test
    @DisplayName("Get Students By Name Containing ' Max ' test")
    void getStudentsByNameContainingMaxSpaceTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.studentDAO.findByFirstNameContainingOrLastNameContaining(" Max " , " Max ")).thenReturn(Collections.singletonList(mockStudent));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);

        List<StudentDTO> studentDTOByNameMax = studentService.getStudents(" Max ");

        Assertions.assertAll(
                () -> Assertions.assertEquals(studentDTOByNameMax.size(), 1),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(0), mockStudentDTO)
        );
    }


    @Test
    @DisplayName("Get Students By Name Containing 'Lencina' test")
    void getStudentsByNameContainingLencinaTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Student mockStudent2 = StudentTestUtils.createStudentFrom("Fede", "Lencina", "flencina@gmail.com", 2);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO2 = StudentTestUtils.createStudentDTOFrom("Fede", "Lencina", "flencina@gmail.com", 2);

        Mockito.when(this.studentDAO.findByFirstNameContainingOrLastNameContaining("Lencina" , "Lencina")).thenReturn(Arrays.asList(mockStudent, mockStudent2));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);
        Mockito.when(this.modelMapper.map(mockStudent2, StudentDTO.class)).thenReturn(mockStudentDTO2);

        List<StudentDTO> studentDTOByNameMax = studentService.getStudents("Lencina");

        Assertions.assertAll(
                () -> Assertions.assertEquals(studentDTOByNameMax.size(), 2),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(0), mockStudentDTO),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(1), mockStudentDTO2)
        );
    }

    @Test
    @DisplayName("Get Students By Name Containing ' Lencina ' test")
    void getStudentsByNameContainingLencinaSpaceTest(){
        Student mockStudent = StudentTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Student mockStudent2 = StudentTestUtils.createStudentFrom("Fede", "Lencina", "flencina@gmail.com", 2);
        StudentDTO mockStudentDTO = StudentTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        StudentDTO mockStudentDTO2 = StudentTestUtils.createStudentDTOFrom("Fede", "Lencina", "flencina@gmail.com", 2);

        Mockito.when(this.studentDAO.findByFirstNameContainingOrLastNameContaining("Lencina" , "Lencina")).thenReturn(Arrays.asList(mockStudent, mockStudent2));
        Mockito.when(this.modelMapper.map(mockStudent, StudentDTO.class)).thenReturn(mockStudentDTO);
        Mockito.when(this.modelMapper.map(mockStudent2, StudentDTO.class)).thenReturn(mockStudentDTO2);

        List<StudentDTO> studentDTOByNameMax = studentService.getStudents("Lencina");

        Assertions.assertAll(
                () -> Assertions.assertEquals(studentDTOByNameMax.size(), 2),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(0), mockStudentDTO),
                () -> Assertions.assertEquals(studentDTOByNameMax.get(1), mockStudentDTO2)
        );
    }


}
