package com.university.demo.model.dto;

import com.university.demo.model.dto.base.BaseDTO;
import com.university.demo.model.entity.Course;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.Review;
import com.university.demo.model.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO extends BaseDTO {

    private Integer id;

    private String title;

    private Instructor instructor;

    private List<Review> reviews = new ArrayList<>();

    private List<Student> students = new ArrayList<>();



}
