package com.university.demo.dao;

import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Supplier;

@DataJpaTest
@ActiveProfiles("test")
public class StudentRepositoryEmbeddedTest {

    @Autowired
    private StudentDAO studentDAO;

    @Test
    @DisplayName("Save Student Test")
    void saveStudentTest(){
        Student student = createFrom("Max", "Lencina", "mlencina@gmail.com", null);
        Student savedStudent = this.studentDAO.save(student);
        Assertions.assertThat(savedStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
    }

    @Test
    @DisplayName("Get Student By Id Test")
    void getStudentByIdTest(){
        Student student = createFrom("Max", "Lencina", "mlencina@gmail.com", null);
        Student savedStudent = this.studentDAO.save(student);

        Student retrievedStudent = this.studentDAO.findById(1).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Student mockSavedStudent = createFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> org.junit.jupiter.api.Assertions.assertEquals(retrievedStudent.getId(), Integer.valueOf(1)),
                () -> org.junit.jupiter.api.Assertions.assertEquals(retrievedStudent, mockSavedStudent)
        );
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Have Students From SQL File")
    void shouldHaveStudentsThroughSqlFileTest(){
        List<Student> students = this.studentDAO.findAll();
        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students.size()).isEqualTo(3);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Is first student Max")
    void firstStudentShouldBeMax(){
        List<Student> students = this.studentDAO.findAll();
        Student mockSavedStudent = createFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Assertions.assertThat(students.get(0)).isEqualTo(mockSavedStudent);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Get Students By 'Max' name From SQL File")
    void shouldGetStudentsByMaxNameFromSqlFile(){
        List<Student> students = this.studentDAO.findByFirstNameContainingOrLastNameContaining("Max", " ");
        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students.size()).isEqualTo(1);
    }


    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Get Students By 'Lencina' last name From SQL File")
    void shouldGetStudentsByLencinaLastNameFromSqlFile(){
        List<Student> students = this.studentDAO.findByFirstNameContainingOrLastNameContaining(" ", "Lencina");
        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students.size()).isEqualTo(2);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Get Students By 'Max' First Name and 'Lencina' last name From SQL File")
    void shouldGetStudentsByMaxFirstNameAndLencinaLastNameFromSqlFile(){
        List<Student> students = this.studentDAO.findByFirstNameContainingOrLastNameContaining("Max", "Lencina");
        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students.size()).isEqualTo(2);
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Get Students Without First Name and last name From SQL File")
    void shouldNotGetStudentsWithOutFirstAndLastNameFromSqlFile(){
        List<Student> students = this.studentDAO.findByFirstNameContainingOrLastNameContaining(" ", " ");
        Assertions.assertThat(students).isEmpty();
    }

    @Test
    @Sql("classpath:test-data.sql")
    @DisplayName("Should Edit Student with name 'Max'")
    void shouldEditStudentWithNameMax(){
        Student editedStudentMock = createFrom("Max", "Lencina", "maxlencina@gmail.com", 1);

        Student student = this.studentDAO.findById(1).orElseThrow(() -> new EntityNotFoundException("Student not Found"));
        student.setEmail("maxlencina@gmail.com");
        student = this.studentDAO.save(student);

        Assertions.assertThat(student).isEqualTo(editedStudentMock);

        Student editedStudent = this.studentDAO.findAll().get(0);
        Assertions.assertThat(editedStudent).isEqualTo(editedStudentMock);
    }



    private static Student createFrom(String firstName, String lastName, String email, Integer studentId){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setId(studentId);
        return student;
    }

}
