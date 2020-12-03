package com.university.demo.service.base;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.CourseDTO;
import com.university.demo.model.dto.StudentDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getCourses();

    CourseDTO getCourseById(@NotNull Integer courseId);

    CourseDTO addCourse(CourseDTO courseDTO);

    CourseDTO editCourse(CourseDTO courseDTO);

    CourseDTO deleteCourse(CourseDTO courseDTO);

    List<CourseDTO> getCourses(String name);
}
