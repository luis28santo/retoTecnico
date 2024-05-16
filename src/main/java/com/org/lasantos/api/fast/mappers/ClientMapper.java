package com.org.lasantos.api.fast.mappers;

import com.org.lasantos.api.fast.models.dto.ClientDto;
import com.org.lasantos.api.fast.models.dto.CreateClientDto;
import com.org.lasantos.api.fast.models.dto.UpdateClientDto;
import com.org.lasantos.api.fast.models.entity.Client;

public class ClientMapper {

    static public Client dtoToEntity(ClientDto clientDto) {

        return Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .documentNumber(clientDto.getDocumentNumber())
                .documentType(clientDto.getDocumentType())
                .build();
    }

    static public Client createDtoToEntity(CreateClientDto createClientDto) {

        return Client.builder()
                .name(createClientDto.getName())
                .lastName(createClientDto.getLastName())
                .documentNumber(createClientDto.getDocumentNumber())
                .documentType(createClientDto.getDocumentType())
                .build();
    }

    static public Client updateDtoToEntity(UpdateClientDto updateClientDto) {

        return Client.builder()
                .name(updateClientDto.getName())
                .lastName(updateClientDto.getLastName())
                .documentNumber(updateClientDto.getDocumentNumber())
                .documentType(updateClientDto.getDocumentType())
                .build();
    }

    static public ClientDto entityToDto(Client clientDto) {

        return ClientDto.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .documentNumber(clientDto.getDocumentNumber())
                .documentType(clientDto.getDocumentType())
                .build();
    }

}
