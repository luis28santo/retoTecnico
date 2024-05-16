package com.org.lasantos.api.fast.models.dto;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class ClientDto {

    private String id;

    private String name;

    private String lastName;

    private String documentType;

    private String documentNumber;
}
