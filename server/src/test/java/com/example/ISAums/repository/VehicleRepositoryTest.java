package com.example.ISAums.repository;

import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class VehicleRepositoryTest {

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void search() {
    }

    @Test
    void checkAvailability() {
    }

    @Test
    void findByRentACar_Id() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes");
        vehicle.setId(UUID.fromString("74495b25-4905-407f-83e3-c55f85d2c0c7"));
        RentACar rentACar = mock(RentACar.class);
        vehicle.setRentACar(rentACar);
        entityManager.persist(vehicle);
        entityManager.flush();

        Vehicle testVehicle = vehicleRepository.findById(vehicle.getId()).get();

        assertEquals(vehicle.getBrand(), testVehicle.getBrand());
    }
}