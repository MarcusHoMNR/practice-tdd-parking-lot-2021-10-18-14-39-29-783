package com.parkinglot;

import com.parkinglot.Exception.UnrecognizedTicketExpection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.parkinglot.Exception.ExceptionConstant.UNRECOGNIZED_TICKET_EXCEPTION;
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
    void should_fetch_two_when_fetchCars_given_parking_boy_manage_two_parking_lot_two_ticket_and_customer() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");
        Car targetCar2 = new Car("car002");
        parkingBoy.parkCars(customer, new ArrayList<>(Arrays.asList(targetCar, targetCar2)));

        //when
        List<Car> fetchedCarList = parkingBoy.fetchCars(customer);

        //then
        assertAll(
                () -> assertFalse(fetchedCarList.isEmpty()),
                () -> assertEquals(2, fetchedCarList.size()),
                () -> assertEquals(targetCar, fetchedCarList.get(0)),
                () -> assertEquals(targetCar2, fetchedCarList.get(1))
        );
    }

    @Test
    void should_throw_when_fetchCars_given_parking_boy_manage_two_parking_unrecogniced_ticket_and_customer() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();

        //when
        //then
        UnrecognizedTicketExpection exceptionMessage = assertThrows(UnrecognizedTicketExpection.class, () -> parkingBoy.fetchCars(customer));
        assertEquals(UNRECOGNIZED_TICKET_EXCEPTION, exceptionMessage.getMessage());
    }

    @Test
    void should_throw_when_fetchCars_given_parking_boy_manage_two_parking_used_ticket_and_customer() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");
        parkingBoy.parkCars(customer, new ArrayList<>(Arrays.asList(targetCar)));


        parkingBoy.fetchCars(customer);
        //when
        //then
        UnrecognizedTicketExpection exceptionMessage = assertThrows(UnrecognizedTicketExpection.class, () -> parkingBoy.fetchCars(customer));
        assertEquals(UNRECOGNIZED_TICKET_EXCEPTION, exceptionMessage.getMessage());
    }
}
