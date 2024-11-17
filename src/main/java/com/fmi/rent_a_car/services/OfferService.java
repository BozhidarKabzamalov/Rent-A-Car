package com.fmi.rent_a_car.services;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.entities.Offer;
import com.fmi.rent_a_car.mappers.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OfferService {
    private JdbcTemplate db;

    public OfferService(JdbcTemplate jdbc) {
        this.db = jdbc;
    }

    public boolean createOffer(Car car) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO OFFERS (client_id, car_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)");
        this.db.update(query.toString(), car.getModel(), car.getLocation(), car.getDailyPrice());
        return true;
    }

    public List<Offer> getAllOffersForClient(int clientId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM OFFERS WHERE id = ").append(clientId);
        return this.db.query(query.toString(), new CarRowMapper());
    }
}
