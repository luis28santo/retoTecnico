package com.org.lasantos.api.fast.exceptions.errorResponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ErrorForm extends ErrorMessage {

    private List<String> errorForm;

    public ErrorForm(Integer status, String message, String description, List<String> errorForm) {
        super(status, message, description);
        this.errorForm = errorForm;
    }


}
