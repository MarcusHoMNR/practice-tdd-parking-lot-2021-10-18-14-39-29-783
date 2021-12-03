package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_park_in_first_lot_when_parkCars_given_parking_boy_manage_one_parking_lot__and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Customer customer = new Customer();
        Car targetCar = new Car("car001");

        //when
        parkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //then
        assertAll(
                () -> assertEquals(1, customer.getTicketList().size()),
                () -> assertNotNull(customer.getTicketList().get(0))
        );
    }
}
