package com.example.ISAums.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.HotelRepository;
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
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepositoryMock;

    /*
    Kako servis koristi metode repozitorijuma koji smo mokovali, moramo taj mokovani repozitorijum injektovati
    dodavanjem anotacije @InjectMocks.
     */
    @InjectMocks
    private HotelService hotelService;

    @Mock
    private AddressRepository addressRepositoryMock;

    @Test
    public void testGet() {
        when(hotelRepositoryMock.findAllByFilters(null, null, "Hotel1", "null", "null" )).thenReturn(Arrays.asList(Hotel.builder()
                .name("Hotel1")
                .description("DES")
                .rating(0.5)
                .build()));

        List<Hotel> hotels = hotelService.get(null, null, "Hotel1", "null", "null" );
        assertThat(hotels).hasSize(1);

        verify(hotelRepositoryMock, times(1)).findAllByFilters(null, null, "Hotel1", "null", "null");
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAdd() {

        when(hotelRepositoryMock.existsByName("Hotel212312312312321")).thenReturn(false);

        if (hotelRepositoryMock.existsByName("Hotel212312312312321")) {
            throw new EntityAlreadyExistsException("Hotel212312312312321");
        }

        Address address = Address.builder()
                .id(UUID.fromString("75dbb2af-ac45-4ddd-8640-d36c145a9489"))
                .state("Srbija")
                .city("Beogradf")
                .street("Ulica")
                .longitude(24.2)
                .latitude(52.0)
                .build();

        when(addressRepositoryMock.save(address)).thenReturn(address);

        addressRepositoryMock.save(address);

        Hotel hotel = Hotel.builder()
                .name("Hotel212312312312321")
                .description("DES")
                .address(address)
                .build();

        when(hotelRepositoryMock.save(hotel)).thenReturn(hotel);
        Hotel newHotel = hotelRepositoryMock.save(hotel);

        assertThat(newHotel.getName()).isEqualTo("Hotel212312312312321");
        assertThat(newHotel.getDescription()).isEqualTo("DES");
        verify(hotelRepositoryMock, times(1)).save(hotel);

    }

    @Test(expected = EntityAlreadyExistsException.class)
    @Transactional
    @Rollback(true)
    public void testAdd2() {

        when(hotelRepositoryMock.existsByName("Hotel212312312312321")).thenReturn(true);

        if (hotelRepositoryMock.existsByName("Hotel212312312312321")) {
            throw new EntityAlreadyExistsException("Hotel212312312312321");
        }

        Address address = Address.builder()
                .id(UUID.fromString("75dbb2af-ac45-4ddd-8640-d36c145a9489"))
                .state("Srbija")
                .city("Beogradf")
                .street("Ulica")
                .longitude(24.2)
                .latitude(52.0)
                .build();

        when(addressRepositoryMock.save(address)).thenReturn(address);

        addressRepositoryMock.save(address);

        Hotel hotel = Hotel.builder()
                .name("Hotel212312312312321")
                .description("DES")
                .address(address)
                .build();

        when(hotelRepositoryMock.save(hotel)).thenReturn(hotel);
        Hotel newHotel = hotelRepositoryMock.save(hotel);

        assertThat(newHotel.getName()).isEqualTo("Hotel212312312312321");
        assertThat(newHotel.getDescription()).isEqualTo("DES");
        verify(hotelRepositoryMock, times(1)).save(hotel);

    }
}
