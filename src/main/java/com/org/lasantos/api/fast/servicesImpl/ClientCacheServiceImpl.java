package com.org.lasantos.api.fast.servicesImpl;

import com.org.lasantos.api.fast.exceptions.customException.NotFoundCustomException;
import com.org.lasantos.api.fast.mappers.ClientMapper;
import com.org.lasantos.api.fast.models.dto.ClientDto;
import com.org.lasantos.api.fast.models.dto.CreateClientDto;
import com.org.lasantos.api.fast.models.dto.UpdateClientDto;
import com.org.lasantos.api.fast.models.entity.Client;
import com.org.lasantos.api.fast.repositories.ClientRepository;
import com.org.lasantos.api.fast.services.ClientService;

import java.util.*;
import java.util.stream.Collectors;

public class ClientCacheServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private List<Client> clients = new ArrayList<>();

    public ClientCacheServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(CreateClientDto createClientDto) {
        Client client = ClientMapper.createDtoToEntity(createClientDto);
        client.setId(UUID.randomUUID().toString());
        clients.add(client);

        return ClientMapper.entityToDto(client);
    }

    @Override
    public List<ClientDto> getAll() {
        return clients.stream().map(ClientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> findById(String id) {
        Client client = clients.stream().filter(c -> c.getId().equals(id)).findFirst().orElseThrow(() -> new NotFoundCustomException("No existe el cliente."));

        return Optional.of(ClientMapper.entityToDto(client));
    }

    @Override
    public ClientDto update(String id, UpdateClientDto clientDto) {
        Optional<Client> clientOptional = clients.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (!clientOptional.isPresent()) {
            throw new NotFoundCustomException("No existe el cliente.");
        }

        Client client = ClientMapper.updateDtoToEntity(clientDto);
        client.setId(id);

        clients = clients.stream().map(oldClient -> {
            if (oldClient.getId().equals(id)) {
                return client;
            }

            return oldClient;
        }).collect(Collectors.toList());

        return ClientMapper.entityToDto(client);
    }


}
