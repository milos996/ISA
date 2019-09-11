package com.example.ISAums.dto.database;

import lombok.Data;

import java.util.UUID;

@Data
public class DBRoom {

    private UUID id;

    private Integer number;

    private Integer floor;

    private Double priceSummer;

    private Double priceWinter;

    private Double priceSpring;

    private Double priceAutumn;

    private Integer numberOfPeople;

    public DBRoom(UUID id, Integer number, Integer floor, Double priceSummer, Double priceWinter, Double priceSpring, Double priceAutumn, Integer numberOfPeople) {
        this.number = number;
        this.floor = floor;
        this.priceSummer = priceSummer;
        this.priceWinter = priceWinter;
        this.priceSpring = priceSpring;
        this.priceAutumn = priceAutumn;
        this.numberOfPeople = numberOfPeople;
    }
}
