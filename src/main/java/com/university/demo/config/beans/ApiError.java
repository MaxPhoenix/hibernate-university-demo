package com.university.demo.config.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ApiError implements Serializable {

    private HttpStatus statusCode;
    private String errorMesage;

    public ApiError(HttpStatus statusCode, String errorMesage) {
        this.statusCode = statusCode;
        this.errorMesage = errorMesage;
    }

}
