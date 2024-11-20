package com.fmi.rent_a_car.mappers;

import com.fmi.rent_a_car.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

        Client client = new Client();

        client.setId(rs.getInt("id"));
        client.setName(rs.getString("name"));
        client.setLocation(rs.getString("location"));
        client.setPhoneNumber(rs.getString("phone_number"));
        client.setAge(rs.getInt("age"));
        client.setHasAccidents(rs.getBoolean("has_accidents"));

        return client;
    }
}
