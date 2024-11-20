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
        offer.setCarId(rs.getInt("car_id"));
        offer.setClientId(rs.getInt("client_id"));
        offer.setWeekDaysCount(rs.getInt("week_days_count"));
        offer.setWeekendDaysCount(rs.getInt("weekend_days_count"));
        offer.setTotalPrice(rs.getBigDecimal("total_price"));
        offer.setAccepted(rs.getBoolean("is_accepted"));
        offer.setIsActive(rs.getBoolean("is_active"));

        return offer;
    }
}