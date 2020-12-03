package com.university.demo.mapper;

import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.InstructorDetailDTO;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.InstructorDetail;
import com.university.demo.model.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapperTest {

    @Test
    @DisplayName("Student to dto mapper test")
    void mapStudentToStudentDtoTest(){
        ModelMapper modelMapper = new ModelMapper();
        Student student = this.getStudent("Max", "Lencina", "mlencina@gmail.com", 1);

        StudentDTO mappedStudentDTO = modelMapper.map(student, StudentDTO.class);
        StudentDTO sampleStudentDTO = this.getStudentDTO("Max", "Lencina", "mlencina@gmail.com", 1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(mappedStudentDTO, sampleStudentDTO),
                () -> Assertions.assertEquals(mappedStudentDTO.getFirstName(), sampleStudentDTO.getFirstName()),
                () -> Assertions.assertEquals(mappedStudentDTO.getLastName(), sampleStudentDTO.getLastName()),
                () -> Assertions.assertEquals(mappedStudentDTO.getEmail(), sampleStudentDTO.getEmail()),
                () -> Assertions.assertEquals(mappedStudentDTO.getId(), sampleStudentDTO.getId())
        );
    }

    @Test
    @DisplayName("StudentDto to entity mapper test")
    void mapStudentDtoToStudentTest(){
        ModelMapper modelMapper = new ModelMapper();
        StudentDTO student = this.getStudentDTO("Max", "Lencina", "mlencina@gmail.com", 1);

        Student mappedStudent = modelMapper.map(student, Student.class);
        Student sampleStudent = this.getStudent("Max", "Lencina", "mlencina@gmail.com", 1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(mappedStudent, sampleStudent),
                () -> Assertions.assertEquals(mappedStudent.getFirstName(), sampleStudent.getFirstName()),
                () -> Assertions.assertEquals(mappedStudent.getLastName(), sampleStudent.getLastName()),
                () -> Assertions.assertEquals(mappedStudent.getEmail(), sampleStudent.getEmail()),
                () -> Assertions.assertEquals(mappedStudent.getId(), sampleStudent.getId())
        );
    }

    @Test
    @DisplayName("StudentDtos to entities mapper test")
    void mapStudentDtosToStudentsTest(){
        ModelMapper modelMapper = new ModelMapper();

        List<Student> students = this.getStudents();
        List<StudentDTO> mappedStudentDtos = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());


        List<StudentDTO> sampleStudentDtos = this.getStudentsDTO();
        Assertions.assertArrayEquals(mappedStudentDtos.toArray(), sampleStudentDtos.toArray());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mappedStudentDtos.get(0), sampleStudentDtos.get(0)),
                () -> Assertions.assertEquals(mappedStudentDtos.get(1), sampleStudentDtos.get(1))
        );
    }

    @Test
    @DisplayName("Students to dtos mapper test")
    void mapStudentToStudentsDtosTest(){
        ModelMapper modelMapper = new ModelMapper();

        List<StudentDTO> studentsDTO = this.getStudentsDTO();
        List<Student> mappedStudents = studentsDTO
                .stream()
                .map(studentDTO -> modelMapper.map(studentDTO, Student.class))
                .collect(Collectors.toList());


        List<Student> sampleStudents = this.getStudents();
        Assertions.assertArrayEquals(mappedStudents.toArray(), sampleStudents.toArray());

        Assertions.assertAll(
                () -> Assertions.assertEquals(mappedStudents.get(0), sampleStudents.get(0)),
                () -> Assertions.assertEquals(mappedStudents.get(1), sampleStudents.get(1))
        );
    }

    private Student getStudent(String firstName, String lastName, String email, Integer id){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setFirstName(lastName);
        student.setFirstName(email);
        student.setId(id);
        return student;
    }

    private StudentDTO getStudentDTO(String firstName, String lastName, String email, Integer id){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(firstName);
        studentDTO.setFirstName(lastName);
        studentDTO.setFirstName(email);
        studentDTO.setId(id);
        return studentDTO;
    }


    private List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(this.getStudent("Max", "Lencina", "mlencina@gmail.com", 1));
        students.add(this.getStudent("Fede", "Lencina", "flencina@gmail.com", 1));
        return students;
    }

    private List<StudentDTO> getStudentsDTO(){
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentDTOS.add(this.getStudentDTO("Max", "Lencina", "mlencina@gmail.com", 1));
        studentDTOS.add(this.getStudentDTO("Fede", "Lencina", "flencina@gmail.com", 1));
        return studentDTOS;
    }

    @Test
    @DisplayName("Nested Object mapper test")
    void mapInstructorToDtoTest(){
        Instructor instructor = this.getInstructor();
        ModelMapper modelMapper = new ModelMapper();

        InstructorDTO instructorDTO = modelMapper.map(instructor, InstructorDTO.class);
        InstructorDTO sampleInstructorDTO = this.getInstructorDTO();

        Assertions.assertAll(
                () -> Assertions.assertEquals(instructorDTO, sampleInstructorDTO),
                () -> Assertions.assertEquals(instructorDTO.getFirstName(), sampleInstructorDTO.getFirstName()),
                () -> Assertions.assertEquals(instructorDTO.getLastName(), sampleInstructorDTO.getLastName()),
                () -> Assertions.assertEquals(instructorDTO.getEmail(), sampleInstructorDTO.getEmail()),
                () -> Assertions.assertEquals(instructorDTO.getId(), sampleInstructorDTO.getId()),
                () -> Assertions.assertEquals(instructorDTO.getInstructorDetail(), sampleInstructorDTO.getInstructorDetail()),
                () -> Assertions.assertEquals(instructorDTO.getInstructorDetail().getHobby(), sampleInstructorDTO.getInstructorDetail().getHobby()),
                () -> Assertions.assertEquals(instructorDTO.getInstructorDetail().getId(), sampleInstructorDTO.getInstructorDetail().getId()),
                () -> Assertions.assertEquals(instructorDTO.getInstructorDetail().getYoutubeChannel(), sampleInstructorDTO.getInstructorDetail().getYoutubeChannel()),
                () -> Assertions.assertEquals(instructorDTO.getInstructorDetail().getInstructor(), sampleInstructorDTO.getInstructorDetail().getInstructor())
        );
    }

    @Test
    @DisplayName("Nested Object dto to entity mapper test")
    void mapInstructorDtoToEntityTest(){
        InstructorDTO instructorDTO = this.getInstructorDTO();
        ModelMapper modelMapper = new ModelMapper();

        Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
        Instructor sampleInstructor = this.getInstructor();

        Assertions.assertAll(
                () -> Assertions.assertEquals(instructor, sampleInstructor),
                () -> Assertions.assertEquals(instructor.getFirstName(), sampleInstructor.getFirstName()),
                () -> Assertions.assertEquals(instructor.getLastName(), sampleInstructor.getLastName()),
                () -> Assertions.assertEquals(instructor.getEmail(), sampleInstructor.getEmail()),
                () -> Assertions.assertEquals(instructor.getId(), sampleInstructor.getId()),
                () -> Assertions.assertEquals(instructor.getInstructorDetail(), sampleInstructor.getInstructorDetail()),
                () -> Assertions.assertEquals(instructor.getInstructorDetail().getHobby(), sampleInstructor.getInstructorDetail().getHobby()),
                () -> Assertions.assertEquals(instructor.getInstructorDetail().getId(), sampleInstructor.getInstructorDetail().getId()),
                () -> Assertions.assertEquals(instructor.getInstructorDetail().getYoutubeChannel(), sampleInstructor.getInstructorDetail().getYoutubeChannel())
        );
    }

    private Instructor getInstructor(){
        Instructor instructor = new Instructor();
        InstructorDetail instructorDetail = this.getInstructorDetail();
        instructor.setFirstName("Max");
        instructor.setLastName("Lencina");
        instructor.setEmail("mlencina@youtube.com");
        instructor.setId(1);
        instructor.setInstructorDetail(instructorDetail);
        instructor.getInstructorDetail().setInstructor(instructor);
        return instructor;
    }


    private InstructorDetail getInstructorDetail(){
        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setYoutubeChannel("youtube/max");
        instructorDetail.setHobby("videogames");
        instructorDetail.setId(1);
        return instructorDetail;
    }

    private InstructorDTO getInstructorDTO(){
        InstructorDTO instructorDTO = new InstructorDTO();
        InstructorDetailDTO instructorDetail = this.getInstructorDetailDTO();
        instructorDTO.setFirstName("Max");
        instructorDTO.setLastName("Lencina");
        instructorDTO.setEmail("mlencina@youtube.com");
        instructorDTO.setId(1);
        instructorDTO.setInstructorDetail(instructorDetail);
        instructorDTO.getInstructorDetail().setInstructor(instructorDTO);
        return instructorDTO;
    }


    private InstructorDetailDTO getInstructorDetailDTO(){
        InstructorDetailDTO instructorDetailDTO = new InstructorDetailDTO();
        instructorDetailDTO.setYoutubeChannel("youtube/max");
        instructorDetailDTO.setHobby("videogames");
        instructorDetailDTO.setId(1);
        return instructorDetailDTO;
    }
}
