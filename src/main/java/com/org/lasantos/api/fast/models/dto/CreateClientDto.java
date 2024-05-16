package com.org.lasantos.api.fast.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder(toBuilder = true)
public class CreateClientDto {

    @NotNull(message = "El nombre es requerido.")
    private String name;

    private String lastName;

    @NotNull(message = "El tipo de documento es requerido.")
    private String documentType;

    @NotNull(message = "El N° de documento es requerido.")
    @NotEmpty(message = "El N° de documento no puede ser vacio.")
    private String documentNumber;

    @NotNull(message = "El codigo es requerido.")
    @Length(min = 6, max = 6, message = "El codigo debe tener 6 digitos.")
    private String uniqueCode;

}
