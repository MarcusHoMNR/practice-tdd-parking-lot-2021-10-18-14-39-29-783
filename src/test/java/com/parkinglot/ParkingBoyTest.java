package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {
    @Test
    void should_park_in_first_lot_when_parkCars_given_parking_boy_manage_one_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
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

    @Test
    void should_park_in_first_lot_when_parkCars_given_parking_boy_manage_two_parking_lot_first_not_full_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");

        //when
        parkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //then
        assertAll(
                () -> assertNotNull(parkingLot1.getParkedCarList()),
                () -> assertEquals(1, parkingLot1.getParkedCarList().size()),
                () -> assertEquals(targetCar, parkingLot1.getParkedCarList().get(0)),
                () -> assertTrue(parkingLot2.getParkedCarList().isEmpty())
        );
    }

    @Test
    void should_park_in_second_lot_when_parkCars_given_parking_boy_manage_two_parking_lot_first_full_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");

        //when
        parkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //then
        assertAll(
                () -> assertFalse(parkingLot2.getParkedCarList().isEmpty()),
                () -> assertEquals(1, parkingLot2.getParkedCarList().size()),
                () -> assertEquals(targetCar, parkingLot2.getParkedCarList().get(0)),
                () -> assertTrue(parkingLot1.getParkedCarList().isEmpty())
        );
    }

    @Test
    void should_fetch_in_first_lot_when_fetchCars_given_parking_boy_manage_two_parking_lot_first_not_full_and_customer() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");
        parkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //when
        List<Car> fetchedCarList = parkingBoy.fetchCars(customer);

        //then
        assertAll(
                () -> assertFalse(fetchedCarList.isEmpty()),
                () -> assertEquals(1, fetchedCarList.size()),
                () -> assertEquals(targetCar, fetchedCarList.get(0))
        );
    }
}
