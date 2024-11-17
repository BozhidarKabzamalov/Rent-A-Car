package com.fmi.rent_a_car.controllers;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.http.AppResponse;
import com.fmi.rent_a_car.services.CarService;
import com.fmi.rent_a_car.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/{clientId}")
    public ResponseEntity<?> getAllOffersForClient(@PathVariable int clientId) {
        ArrayList<Car> collection = (ArrayList<Car>) this.offerService.getAllOffersForClient(clientId);

        return AppResponse.success().withData(collection).build();
    }
}
