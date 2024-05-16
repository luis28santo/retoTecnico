package com.org.lasantos.api.fast.exceptions.customException;

public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException(String message) {
        super(message);
    }

}
