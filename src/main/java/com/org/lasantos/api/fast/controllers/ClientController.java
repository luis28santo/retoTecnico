package com.org.lasantos.api.fast.controllers;

import com.org.lasantos.api.fast.exceptions.customException.DtoValidationException;
import com.org.lasantos.api.fast.exceptions.errorResponse.ErrorMessage;
import com.org.lasantos.api.fast.models.dto.ClientDto;
import com.org.lasantos.api.fast.models.dto.CreateClientDto;
import com.org.lasantos.api.fast.models.dto.UpdateClientDto;
import com.org.lasantos.api.fast.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(@Qualifier("clientCacheService") ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDto>> getAll() {
        return new ResponseEntity(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ClientDto>> getById(@PathVariable("id") String id) {
        return new ResponseEntity(clientService.findById(id), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<ClientDto> save(@Valid @RequestBody CreateClientDto clientDto, BindingResult br) {

        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.toList());

            throw new DtoValidationException("Formulario Incorrecto.", errors);
        }

        return new ResponseEntity<ClientDto>(clientService.save(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") String id, @Valid @RequestBody UpdateClientDto clientDto, BindingResult br) {
        if (br.hasErrors()) {

            List<String> errors = br.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.toList());

            throw new DtoValidationException("Formulario Incorrecto.", errors);
        }

        return new ResponseEntity<ClientDto>(clientService.update(id, clientDto), HttpStatus.OK);
    }

}
