package com.university.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.university.demo.model.dto.base.BaseDTO;
import com.university.demo.model.entity.Course;
import com.university.demo.model.entity.InstructorDetail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"id" , "firstName", "lastName", "email"})
public class InstructorDTO extends BaseDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonProperty("instructorDetail")
    @JsonIgnoreProperties("instructor")
    private InstructorDetailDTO instructorDetail;

    private List<Course> courses;

    public boolean hasEmptyDetail(){
        return this.instructorDetail != null && (StringUtils.isEmpty(this.instructorDetail.getYoutubeChannel())
                || StringUtils.isEmpty(this.instructorDetail.getHobby()));
    }

    public void removeInstructorDetail(){
        this.instructorDetail.setInstructor(null);
        this.instructorDetail = null;
    }

}
