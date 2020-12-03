package com.university.demo.dao;

import com.university.demo.model.entity.Course;
import com.university.demo.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDAO extends JpaRepository<Course, Integer> {

    List<Course> findByTitleContaining(String title);

}
