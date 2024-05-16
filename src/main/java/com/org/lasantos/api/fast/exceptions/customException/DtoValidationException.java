package com.org.lasantos.api.fast.exceptions.customException;

import java.util.List;

public class DtoValidationException extends RuntimeException {

    List<String> errorsForm;

    public DtoValidationException(String message, List<String> errorsForm) {
        super(message);
        this.errorsForm = errorsForm;
    }

    public List<String> getErrorsForm() {
        return errorsForm;
    }
}
