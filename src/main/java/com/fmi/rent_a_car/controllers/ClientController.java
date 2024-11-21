package com.fmi.rent_a_car.controllers;

import com.fmi.rent_a_car.entities.Client;
import com.fmi.rent_a_car.http.AppResponse;
import com.fmi.rent_a_car.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        if (this.clientService.createClient(client)) {
            return AppResponse.success().withMessage("Client created successfully").build();
        }

        return AppResponse.error()
                .withMessage("Client could not be created")
                .build();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClientById(@PathVariable int id) {
        Client clientResult = this.clientService.getClientById(id);

        if (clientResult == null) {
            return AppResponse.error().withMessage("Client data not found").build();
        }

        return AppResponse.success().withData(clientResult).build();
    }
}
