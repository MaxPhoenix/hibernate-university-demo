package com.university.demo.utils;

import com.university.demo.model.dto.CourseDTO;
import com.university.demo.model.entity.Course;

public class CourseTestUtils {

    public static Course createStudentFrom(String title, Integer courseId){
        Course course = new Course();
        course.setTitle(title);
        course.setId(courseId);
        return course;
    }

    public static CourseDTO createStudentDTOFrom(String title, Integer courseId){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setTitle(title);
        courseDTO.setId(courseId);
        return courseDTO;
    }


}
