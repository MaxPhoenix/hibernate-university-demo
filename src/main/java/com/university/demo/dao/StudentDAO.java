package com.university.demo.dao;

import com.university.demo.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Integer> {

    List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
