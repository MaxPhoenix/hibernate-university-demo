package com.university.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.university.demo.model.dto.base.BaseDTO;
import com.university.demo.model.entity.Instructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false , of = {"id", "youtubeChannel" , "hobby"})
public class InstructorDetailDTO extends BaseDTO {

    private Integer id;

    private String youtubeChannel;

    private String hobby;

    private InstructorDTO instructor;
}
