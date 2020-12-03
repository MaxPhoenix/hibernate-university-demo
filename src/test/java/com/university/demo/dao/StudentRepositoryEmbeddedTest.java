package com.university.demo.dao;

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

@DataJpaTest
@ActiveProfiles("test")
public class StudentRepositoryEmbeddedTest {

    @Autowired
    private StudentDAO studentDAO;

    @Test
    @DisplayName("Save Student Test")
    void saveStudentTest(){
        Student student = this.getStudentSave();
        Student savedStudent = this.studentDAO.save(student);
        Assertions.assertThat(savedStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
    }

    @Test
    @DisplayName("Get Student By Id Test")
    void getStudentByIdTest(){
        Student student = this.getStudentSave();
        Student savedStudent = this.studentDAO.save(student);

        Student retrievedStudent = this.studentDAO.findById(1).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Student mockSavedStudent = this.getSavedMockStudent();
        org.junit.jupiter.api.Assertions.assertAll(
                () -> org.junit.jupiter.api.Assertions.assertEquals(retrievedStudent.getId(), Integer.valueOf(1)),
                () -> org.junit.jupiter.api.Assertions.assertEquals(retrievedStudent, mockSavedStudent)
        );
    }

    public Student getStudentSave(){
        Student student = new Student();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        return student;
    }

    public Student getSavedMockStudent(){
        Student student = new Student();
        student.setFirstName("Max");
        student.setLastName("Lencina");
        student.setEmail("mlencina@gmail.com");
        student.setId(1);
        return student;
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
        Assertions.assertThat(students.get(0)).isEqualTo(this.getSavedMockStudent());
    }

}
