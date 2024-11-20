package com.fmi.rent_a_car.controllers;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.entities.Offer;
import com.fmi.rent_a_car.http.AppResponse;
import com.fmi.rent_a_car.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/{clientId}")
    public ResponseEntity<?> getAllOffersForClient(@RequestParam int clientId) {
        ArrayList<Offer> collection = (ArrayList<Offer>) this.offerService.getAllOffersForClient(clientId);

        return AppResponse.success().withData(collection).build();
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<?> getOfferById(@PathVariable int id) {
        Offer offer = this.offerService.getOfferById(id);

        if (offer == null) {
            return AppResponse.error().withMessage("Car data not found").build();
        }

        return AppResponse.success().withData(offer).build();
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable int id) {
        boolean isDeleteSuccessful = this.offerService.deleteOffer(id);

        if (!isDeleteSuccessful) {
            return AppResponse.error()
                    .withMessage("Offer data not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Offer deleted successfully")
                .build();
    }
}
