package com.org.lasantos.api.fast.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateClientDto {

    @NotNull(message = "El nombre es requerido.")
    private String name;

    private String lastName;

    @NotNull(message = "El tipo de documento es requerido.")
    private String documentType;

    @NotNull(message = "El N° de documento es requerido.")
    @NotEmpty(message = "El N° de documento no puede ser vacio.")
    private String documentNumber;

}
