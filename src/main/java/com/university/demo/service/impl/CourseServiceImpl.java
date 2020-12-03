package com.university.demo.service.impl;

import com.university.demo.config.exception.exceptions.NotRecordInDataBaseException;
import com.university.demo.dao.CourseDAO;
import com.university.demo.model.dto.CourseDTO;
import com.university.demo.model.dto.StudentDTO;
import com.university.demo.model.entity.Course;
import com.university.demo.model.entity.Student;
import com.university.demo.service.base.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;
    private ModelMapper modelMapper;

    public CourseServiceImpl(@Autowired CourseDAO courseDAO, @Autowired ModelMapper modelMapper) {
        this.courseDAO = courseDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        List<Course> courses = this.courseDAO.findAll();
        courseDTOS = courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
        return courseDTOS;
    }

    @Override
    public CourseDTO getCourseById(Integer courseId) {
        Course course = courseDAO.findById(courseId).orElseThrow(NotRecordInDataBaseException::new);
        CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
        return courseDTO;
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        course = this.courseDAO.save(course);
        courseDTO = this.modelMapper.map(course, CourseDTO.class);
        return courseDTO;
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        Course courseToEdit = modelMapper.map(courseDTO, Course.class);
        boolean exists = this.courseDAO.existsById(courseToEdit.getId());
        if(exists) {
            Course savedCourse = this.courseDAO.save(courseToEdit);
            courseDTO = modelMapper.map(savedCourse, CourseDTO.class);
        }
        return courseDTO;
    }

    @Override
    public CourseDTO deleteCourse(CourseDTO courseDTO) {
        Course courseToDelete = modelMapper.map(courseDTO, Course.class);
        boolean exists = this.courseDAO.existsById(courseToDelete.getId());
        if(exists)
            this.courseDAO.delete(courseToDelete);
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getCourses(String name) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        List<Course> courses = this.courseDAO.findByTitleContaining(name);
        courseDTOS = courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
        return courseDTOS;
    }
}
