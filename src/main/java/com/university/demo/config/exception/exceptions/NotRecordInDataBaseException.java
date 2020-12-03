package com.university.demo.config.exception.exceptions;

import com.university.demo.model.entity.base.BaseEntity;

public class NotRecordInDataBaseException extends RuntimeException {

    public NotRecordInDataBaseException() {
        this("No information found for the requested data.");
    }

    public NotRecordInDataBaseException(String message) {
        super(message);
    }

    public NotRecordInDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
