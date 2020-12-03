package com.university.demo.model.dto;

import com.university.demo.model.dto.base.BaseDTO;
import com.university.demo.model.entity.Course;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,  of = {"id", "firstName", "lastName", "email"})
public class StudentDTO extends BaseDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Course> courses = new ArrayList<>();


}
