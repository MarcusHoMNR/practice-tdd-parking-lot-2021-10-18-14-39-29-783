package com.parkinglot;

import com.parkinglot.Exception.UnrecognizedTicketExpection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.parkinglot.Exception.ExceptionConstant.UNRECOGNIZED_TICKET_EXCEPTION;
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

    @Test
    void should_park_in_second_lot_when_parkCars_given_smart_parking_boy_manage_second_parking_lot_more_mt_space_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot(15);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");

        //when
        smartParkingBoy.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        //then
        assertAll(
                () -> assertFalse(parkingLot2.getParkedCarList().isEmpty()),
                () -> assertEquals(1, parkingLot2.getParkedCarList().size()),
                () -> assertEquals(targetCar, parkingLot2.getParkedCarList().get(0)),
                () -> assertTrue(parkingLot1.getParkedCarList().isEmpty())
        );
    }

    @Test
    void should_fetch_two_when_fetchCars_given_Smart_parking_boy_manage_two_parking_lot_two_ticket_and_customer() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        SmartParkingBoy smartParkingBoyparkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
        Customer customer = new Customer();
        Car targetCar = new Car("car001");
        Car targetCar2 = new Car("car002");
        smartParkingBoyparkingBoy.parkCars(customer, new ArrayList<>(Arrays.asList(targetCar, targetCar2)));

        //when
        List<Car> fetchedCarList = smartParkingBoyparkingBoy.fetchCars(customer);

        //then
        assertAll(
                () -> assertFalse(fetchedCarList.isEmpty()),
                () -> assertEquals(2, fetchedCarList.size()),
                () -> assertEquals(targetCar, fetchedCarList.get(0)),
                () -> assertEquals(targetCar2, fetchedCarList.get(1))
        );
    }

    @Test
    void should_throw_when_fetchCars_given_Smart_parking_boy_manage_two_parking_unrecogniced_ticket_and_customer() {
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
}
