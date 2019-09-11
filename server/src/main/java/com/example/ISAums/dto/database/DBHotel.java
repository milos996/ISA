package com.example.ISAums.dto.database;

import com.example.ISAums.model.Address;
import lombok.Data;

import java.util.UUID;

@Data
public class DBHotel {

    private UUID id;

    private String name;

    private String description;

    private Double rating;

    private Address address;

    public DBHotel(UUID id, String name, String description, Double rating, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
    }
}
