package com.university.demo.controller;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.CourseDTO;
import com.university.demo.service.base.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {


    private CourseService courseService;

    public CourseController(@Autowired CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDTO> getCourses(@RequestParam(required = false, value = "name") String name){
        if(!StringUtils.isEmpty(name))
            return this.courseService.getCourses(name);
        return this.courseService.getCourses();
    }

    @GetMapping(value = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO getCourseById(@PathVariable @NotNull Integer courseId){
        return this.courseService.getCourseById(courseId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO){
        return this.courseService.addCourse(courseDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO editCourse(@RequestBody CourseDTO courseDTO){
        return this.courseService.editCourse(courseDTO);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO deleteCourse(@RequestBody CourseDTO courseDTO){
        return this.courseService.deleteCourse(courseDTO);
    }
}
