package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_park_in_first_lot_when_parkCars_given_smart_parking_boy_manage_two_parking_lot_same_mt_space_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");

        //when
        smartParkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //then
        assertAll(
                () -> assertFalse(parkingLot1.getParkedCarList().isEmpty()),
                () -> assertEquals(1, parkingLot1.getParkedCarList().size()),
                () -> assertEquals(targetCar, parkingLot1.getParkedCarList().get(0)),
                () -> assertTrue(parkingLot2.getParkedCarList().isEmpty())
        );
    }
}
