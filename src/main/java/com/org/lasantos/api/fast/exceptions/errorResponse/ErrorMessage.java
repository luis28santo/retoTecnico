package com.org.lasantos.api.fast.exceptions.errorResponse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {

    private Integer status;

    private String message;

    private String description;

}
