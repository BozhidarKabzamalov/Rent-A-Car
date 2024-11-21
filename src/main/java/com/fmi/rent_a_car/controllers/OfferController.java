package com.fmi.rent_a_car.controllers;

import com.fmi.rent_a_car.entities.Offer;
import com.fmi.rent_a_car.http.AppResponse;
import com.fmi.rent_a_car.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public ResponseEntity<?> getAllOffersForClient(@RequestParam int clientId) {
        ArrayList<Offer> collection = (ArrayList<Offer>) this.offerService.getAllOffersForClient(clientId);

        return AppResponse.success().withData(collection).build();
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<?> getOfferById(@PathVariable int id) {
        Offer offer = this.offerService.getOfferById(id);

        if (offer == null) {
            return AppResponse.error().withMessage("Offer data not found").build();
        }

        return AppResponse.success().withData(offer).build();
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        if (this.offerService.createOffer(offer)) {
            return AppResponse.success().withMessage("Offer created successfully").build();
        }

        return AppResponse.error()
                .withMessage("Offer could not be created")
                .build();
    }

    @PutMapping("/offers/{id}/accept")
    public ResponseEntity<?> acceptOffer(@PathVariable int id) {
        boolean isUpdateSuccessful =  this.offerService.acceptOffer(id);

        if(!isUpdateSuccessful) {
            return AppResponse.error()
                    .withMessage("Offer could not be accepted")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Offer was accepted successfully")
                .build();
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
