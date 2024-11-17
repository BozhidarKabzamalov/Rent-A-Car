package com.fmi.rent_a_car.mappers;

import com.fmi.rent_a_car.entities.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Offer offer = new Offer();

        offer.setId(rs.getInt("id"));
        offer.setModel(rs.getString("model"));
        offer.setLocation(rs.getString("location"));
        offer.setDailyPrice(rs.getBigDecimal("daily_price"));

        return offer;
    }
}