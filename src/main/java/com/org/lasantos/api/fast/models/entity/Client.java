package com.org.lasantos.api.fast.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "clientes")
public class Client {

    @Id
    private String id;

    private String name;

    private String lastName;

    private String documentType;

    @Column(unique = true)
    private String documentNumber;

    private String uniqueCode;

    @PrePersist()
    private void addId() {
        id = UUID.randomUUID().toString();
    }

}
