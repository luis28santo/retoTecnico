package com.org.lasantos.api.fast.servicesImpl;

import com.org.lasantos.api.fast.exceptions.customException.NotFoundCustomException;
import com.org.lasantos.api.fast.exceptions.customException.UniqueConstraintException;
import com.org.lasantos.api.fast.mappers.ClientMapper;
import com.org.lasantos.api.fast.models.dto.ClientDto;
import com.org.lasantos.api.fast.models.dto.CreateClientDto;
import com.org.lasantos.api.fast.models.dto.UpdateClientDto;
import com.org.lasantos.api.fast.models.entity.Client;
import com.org.lasantos.api.fast.repositories.ClientRepository;
import com.org.lasantos.api.fast.services.ClientService;
import com.org.lasantos.api.fast.services.EncryptService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EncryptService encryptService;

    public ClientServiceImpl(ClientRepository clientRepository, EncryptService encryptService) {
        this.clientRepository = clientRepository;
        this.encryptService = encryptService;
    }

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream().map(ClientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> findById(String id) {

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new NotFoundCustomException("Cliente no encontrado.");
        }

        return clientOptional.map(ClientMapper::entityToDto);
    }

    @Override
    public ClientDto save(CreateClientDto clientDto) {

        Optional<Client> clientOptional = clientRepository.findByDocumentNumber(clientDto.getDocumentNumber());

        if (clientOptional.isPresent()) {
            throw new UniqueConstraintException("El cliente con N° Documento " + clientDto.getDocumentNumber() + " ya existe.");
        }

        Client newClient = getClient(clientDto);

        return ClientMapper.entityToDto(clientRepository.save(newClient));
    }

    @Override
    public ClientDto update(String id, UpdateClientDto clientDto) throws NotFoundCustomException {

        Client oldClient = clientRepository.findById(id).orElseThrow(() -> new NotFoundCustomException("Cliente no encontrado."));

        Boolean existDocument = clientRepository.existClientByDocumentNumberAndDifferentId(clientDto.getDocumentNumber(), id);

        if (existDocument) {
            throw new UniqueConstraintException("El N° de documento " + clientDto.getDocumentNumber() + " ya existe.");
        }

        Client client = ClientMapper.updateDtoToEntity(clientDto);
        client.setUniqueCode(oldClient.getUniqueCode());
        client.setId(id);

        return ClientMapper.entityToDto(clientRepository.save(client));
    }

    private Client getClient(CreateClientDto clientDto) {
        Client newClient = ClientMapper.createDtoToEntity(clientDto);
        String codeEncrypted = encryptService.encryptCode(clientDto.getUniqueCode());
        newClient.setUniqueCode(codeEncrypted);
        // Boolean verifyCode = encryptService.verifyCode(clientDto.getUniqueCode(), codeEncrypted);
        // log.warn("El codigo y el codigoEncriptado son iguales: {}", verifyCode);

        return newClient;
    }
}
