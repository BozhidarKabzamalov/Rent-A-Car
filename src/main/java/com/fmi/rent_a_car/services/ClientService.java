package com.fmi.rent_a_car.services;

import com.fmi.rent_a_car.entities.Client;
import com.fmi.rent_a_car.mappers.ClientRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final JdbcTemplate db;

    public ClientService(JdbcTemplate jdbc) {
        this.db = jdbc;
    }

    public boolean createClient(Client client) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT into CLIENTS (name, location, phone_number, age, has_accidents) VALUES (?, ?, ?, ?, ?)");
        this.db.update(query.toString(), client.getName(), client.getLocation(), client.getPhoneNumber(), client.getAge(), client.getHasAccidents());
        return true;
    }

    public Client getClientById(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CLIENTS WHERE id = ?");
        var collection = this.db.query(query.toString(), new ClientRowMapper(), id);

        if (collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }
}
