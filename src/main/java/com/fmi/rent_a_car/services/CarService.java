package com.fmi.rent_a_car.services;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.mappers.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private JdbcTemplate db;

    public CarService(JdbcTemplate jdbc) {
        this.db = jdbc;
    }

    public boolean createCar(Car car) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT into CARS (model, location, daily_price) VALUES (?, ?, ?)");
        this.db.update(query.toString(), car.getModel(), car.getLocation(), car.getDailyPrice());
        return true;
    }

    public Car getCarById(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CARS WHERE id = ").append(id);
        var collection = this.db.query(query.toString(), new CarRowMapper());

        if (collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }

    public List<Car> getAllCarsByClientLocation() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CARS WHERE location = 'Plovdiv'");
        return this.db.query(query.toString(), new CarRowMapper());
    }

    public boolean updateCar(Car car) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE CARS ")
                .append("SET model = ?,")
                .append("location = ? ")
                .append("daily_price = ? ")
                .append("WHERE is_active = 1 ")
                .append(" AND id = ?");

        int resultCount = this.db.update(query.toString(), car.getModel(), car.getLocation(), car.getDailyPrice(), car.getId());

        if (resultCount > 1) {
            throw new RuntimeException("More than one customer with same id exists");
        }

        return resultCount == 1;
    }

    public boolean deleteCar(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE CARS ")
                .append("SET is_active = false ")
                .append("WHERE id = ?");

        int resultCount = this.db.update(query.toString(), id);

        if (resultCount > 1) {
            throw new RuntimeException("More than one car with the same id exists");
        }

        return resultCount == 1;
    }
}
