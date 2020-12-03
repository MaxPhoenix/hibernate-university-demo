package com.university.demo.mapper.base;

import com.university.demo.model.dto.base.BaseDTO;
import com.university.demo.model.entity.base.BaseEntity;

public interface DtoEntityMapper <T extends BaseDTO, E extends BaseEntity> {

    T toDto(E entity);

    E toEntity(T dto);
}
