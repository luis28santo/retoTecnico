package com.org.lasantos.api.fast.services;

import com.org.lasantos.api.fast.models.dto.ClientDto;
import com.org.lasantos.api.fast.models.dto.CreateClientDto;
import com.org.lasantos.api.fast.models.dto.UpdateClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDto save(CreateClientDto createClientDto);

    List<ClientDto> getAll();

    Optional<ClientDto> findById(String id);

    ClientDto update(String id, UpdateClientDto clientDto);

}
