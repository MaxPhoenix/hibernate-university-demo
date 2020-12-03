package com.university.demo.model.dto;

import com.university.demo.model.dto.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO extends BaseDTO {

    private Integer id;

    private String comment;
}
