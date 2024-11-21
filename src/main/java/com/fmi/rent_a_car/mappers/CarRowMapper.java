package com.fmi.rent_a_car.mappers;

import com.fmi.rent_a_car.entities.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {

        Car car = new Car();

        car.setId(rs.getInt("id"));
        car.setModel(rs.getString("model"));
        car.setLocation(rs.getString("location"));
        car.setDailyPrice(rs.getBigDecimal("daily_price"));
        car.setIsActive(rs.getBoolean("is_active"));

        return car;
    }
}
