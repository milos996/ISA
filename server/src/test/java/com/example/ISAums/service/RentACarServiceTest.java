package com.example.ISAums.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarServiceTest {

    @Mock
    private RentACarRepository racRepositoryMocked;

    @Mock
    private RentACar racMocked;

    @InjectMocks
    private RentACarService rentACarService;

    @Test
    public void tryToFindRentACarById() {

        when(racRepositoryMocked.findById(UUID.fromString("3f58d3a4-cdd7-4712-8336-69ff555bdf6b"))).thenReturn(java.util.Optional.of(racMocked));
        RentACar rac = rentACarService.findById(UUID.fromString("3f58d3a4-cdd7-4712-8336-69ff555bdf6b"));

        assertEquals(racMocked, rac);
        verify(racRepositoryMocked, times(1)).findById(UUID.fromString("3f58d3a4-cdd7-4712-8336-69ff555bdf6b"));
        verifyNoMoreInteractions(racRepositoryMocked);

    }

    @Test
    @Transactional
    public void tryToCreateNewRentACar() {
        RentACar rac = RentACar
                .builder()
                .name("USM")
                .address(Address
                        .builder()
                        .city("Novi Sad")
                        .state("Serbia")
                        .street("Pap Pavla 42")
                        .latitude(45.2771)
                        .longitude(19.8435)
                        .build())
                .description("Fina vozila")
                .rating(0.0)
                .build();
        when(racRepositoryMocked.findAll()).thenReturn(Arrays.asList(rac));

        RentACar newRac = RentACar
                .builder()
                .name("MUS")
                .address(Address
                        .builder()
                        .city("Novi Sad")
                        .state("Serbia")
                        .street("Pap Pavla 42")
                        .latitude(45.2871)
                        .longitude(19.8535)
                        .build())
                .description("Fina vozila")
                .rating(0.0)
                .build();

        when(racRepositoryMocked.save(newRac)).thenReturn(newRac);
        rentACarService.save(newRac);

        int numOfRacs = rentACarService.findAll().size();

        when(racRepositoryMocked.findAll()).thenReturn(Arrays.asList(rac, newRac));

        List<RentACar> rentACars = rentACarService.findAll();
        assertEquals(rentACars.size(), numOfRacs + 1);

        verify(racRepositoryMocked, times(2)).findAll();
        verify(racRepositoryMocked, times(1)).save(newRac);
        verifyNoMoreInteractions(racRepositoryMocked);
    }

    @Test
    public void tryToFindAllRentACars() {
        when(racRepositoryMocked.findAll()).thenReturn(Arrays.asList(RentACar.builder().build()));
        List<RentACar> rentACars = rentACarService.findAll();

        assertEquals(rentACars.size(), 1);

        verify(racRepositoryMocked, times(1)).findAll();
        verifyNoMoreInteractions(racRepositoryMocked);
    }

}