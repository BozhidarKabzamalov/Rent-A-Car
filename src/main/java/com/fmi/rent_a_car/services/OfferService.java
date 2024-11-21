package com.fmi.rent_a_car.services;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.entities.Client;
import com.fmi.rent_a_car.entities.Offer;
import com.fmi.rent_a_car.mappers.OfferRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OfferService {
    private final JdbcTemplate db;
    private final CarService carService;
    private final ClientService clientService;

    public OfferService(JdbcTemplate jdbc, CarService carService, ClientService clientService) {
        this.db = jdbc;
        this.carService = carService;
        this.clientService = clientService;
    }

    public boolean createOffer(Offer offer) {
        Car car = carService.getCarById(offer.getCarId());

        if (car == null) {
            throw new RuntimeException("Car not found with ID: " + offer.getCarId());
        }

        Client client = clientService.getClientById(offer.getClientId());

        if (client == null) {
            throw new RuntimeException("Client not found with ID: " + offer.getClientId());
        }

        BigDecimal dailyPrice = car.getDailyPrice();
        boolean hasAccidents = client.getHasAccidents();
        BigDecimal weekdaysPrice = dailyPrice.multiply(BigDecimal.valueOf(offer.getWeekDaysCount()));
        BigDecimal weekendPrice = dailyPrice.multiply(BigDecimal.valueOf(1.1)).multiply(BigDecimal.valueOf(offer.getWeekendDaysCount()));
        BigDecimal totalPrice = weekdaysPrice.add(weekendPrice);

        if (hasAccidents) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(200));
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO OFFERS (car_id, client_id, week_days_count, weekend_days_count, total_price) VALUES (?, ?, ?, ?, ?)");

        this.db.update(query.toString(), offer.getCarId(), offer.getClientId(), offer.getWeekDaysCount(), offer.getWeekendDaysCount(), totalPrice);
        return true;
    }

    public boolean acceptOffer(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE OFFERS SET IS_ACCEPTED = TRUE WHERE is_active = TRUE AND id = ?");

        int resultCount = this.db.update(query.toString(), id);

        if (resultCount > 1) {
            throw new RuntimeException("More than one offers with same id exists");
        }

        return resultCount == 1;
    }

    public Offer getOfferById(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM OFFERS WHERE id = ?");
        List<Offer> collection = this.db.query(query.toString(), new OfferRowMapper(), id);

        if (collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }

    public List<Offer> getAllOffersForClient(int clientId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM OFFERS WHERE client_id = ?");

        return this.db.query(query.toString(), new OfferRowMapper(), clientId);
    }

    public boolean deleteOffer(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE OFFERS SET is_active = false WHERE id = ? ");

        int resultCount = this.db.update(query.toString(), id);

        if (resultCount > 1) {
            throw new RuntimeException("More than one offer with the same id exists");
        }

        return resultCount == 1;
    }
}
