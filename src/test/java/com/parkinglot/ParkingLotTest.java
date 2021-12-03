package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_customer_with_ticket_when_parkCar_given_parking_lot_And_Car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("car001");
        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(car)));

        //then
        assertAll(
                () -> assertEquals(1, customer.getTicketList().size()),
                () -> assertNotNull(customer.getTicketList().get(0))
        );
    }

    @Test
    void should_return_null_when_parkCar_given_parking_lot_full_And_Car() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car("car001");
        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(car)));

        //then
        assertTrue(customer.getTicketList().isEmpty());
    }

    @Test
    void should_return_target_car_when_fetchCar_given_customer_with_one_valid_ticket() {
        //given
        Car targetCar = new Car("car001");
        ParkingLot parkingLot = new ParkingLot();
        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));;
        Car fetchedCar = parkingLot.fetchCarByCustomer(customer);

        //then
        assertAll(
                () -> assertNotNull(fetchedCar),
                () -> assertEquals(targetCar.getCarId(), fetchedCar.getCarId())
        );
    }

    @Test
    void should_return_null_when_fetchCar_given_customer_with_no_ticket() {
        //given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot();

        //when
        Car fetchedCar = parkingLot.fetchCarByCustomer(customer);

        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_null_when_fetchCar_given_customer_with_not_issued_ticket() {
        //given
        Car anotherCar = new Car("car001");

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot anotherparkingLot = new ParkingLot();

        Customer customer = new Customer();

        //when
        anotherparkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(anotherCar)));
        Car fetchedCar = parkingLot.fetchCarByCustomer(customer);

        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_null_when_fetchCar_given_customer_with_fetched_ticket() {
        //given
        Car targetCar = new Car("car001");

        ParkingLot parkingLot = new ParkingLot();

        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));
        Car fetchedCarFirstTime = parkingLot.fetchCarByCustomer(customer);
        Car fetchedCarAgain = parkingLot.fetchCarByCustomer(customer);

        //then
        assertAll(
                () -> assertNotNull(fetchedCarFirstTime),
                () -> assertEquals(targetCar.getCarId(), fetchedCarFirstTime.getCarId()),
                () -> assertNull(fetchedCarAgain)
        );
    }

    @Test
    void should_return_multiple_when_fetchCar_given_customer_with_ticketList() {
        //given
        Car targetCar1 = new Car("car001");
        Car targetCar2 = new Car("car002");
        List<Car> targetCarList = new ArrayList<>(Arrays.asList(targetCar1, targetCar2));
        ParkingLot parkingLot = new ParkingLot();
        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, targetCarList);
        List<Car> fetchedCarList = parkingLot.fetchCarListByCustomer(customer);

        //then
        assertAll(
                () -> assertNotNull(fetchedCarList),
                () -> assertEquals(2, fetchedCarList.size()),
                () -> assertEquals(targetCarList.get(0).getCarId(), fetchedCarList.get(0).getCarId()),
                () -> assertEquals(targetCarList.get(1).getCarId(), fetchedCarList.get(1).getCarId())
        );
    }
}
