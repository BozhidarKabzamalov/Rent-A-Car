package com.fmi.rent_a_car.services;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.entities.Client;
import com.fmi.rent_a_car.entities.Offer;
import com.fmi.rent_a_car.mappers.OfferRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OfferService {
    private JdbcTemplate db;
    private CarService carService;
    private ClientService clientService;

    public OfferService(JdbcTemplate jdbc, CarService carService, ClientService clientService) {
        this.db = jdbc;
        this.carService = carService;
        this.clientService = clientService;
    }

    public boolean createOffer(Offer offer) {
        Car car = carService.getCarById(offer.getCarId());
        Client client = clientService.getClientById(offer.getClientId());
        BigDecimal dailyPrice = car.getDailyPrice();
        boolean hasAccidents = client.getHasAccidents();

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO OFFERS (client_id, car_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)");

        this.db.update(query.toString(), offer.getCarId(), offer.getClientId(), offer.getWeekDaysCount(), offer.getWeekendDaysCount());
        return true;
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
        query.append("SELECT * FROM OFFERS WHERE id = ?");

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
