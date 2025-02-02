package com.parkinglot;

import com.parkinglot.Exception.FullParkingLotException;
import com.parkinglot.Exception.UnrecognizedTicketExpection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.parkinglot.Exception.ExceptionConstant.FULL_PARKING_LOT_EXCEPTION;
import static com.parkinglot.Exception.ExceptionConstant.UNRECOGNIZED_TICKET_EXCEPTION;
import static org.junit.jupiter.api.Assertions.*;

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
    void should_throw_when_parkCar_given_parking_lot_full_And_Car() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car("car001");
        Customer customer = new Customer();

        //when
        //then
        FullParkingLotException exceptionMessage = assertThrows(FullParkingLotException.class, () -> parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(car))));
        assertEquals(FULL_PARKING_LOT_EXCEPTION, exceptionMessage.getMessage());
    }

    @Test
    void should_return_target_car_when_fetchCar_given_customer_with_one_valid_ticket() {
        //given
        Car targetCar = new Car("car001");
        ParkingLot parkingLot = new ParkingLot();
        Customer customer = new Customer();

        //when
        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));

        List<Car> fetchedCarList = parkingLot.fetchCarListByCustomer(customer);

        //then
        assertAll(
                () -> assertNotNull(fetchedCarList),
                () -> assertEquals(1, fetchedCarList.size()),
                () -> assertEquals(targetCar.getCarId(), fetchedCarList.get(0).getCarId())
        );
    }

    @Test
    void should_throw_when_fetchCar_given_customer_with_no_ticket() {
        //given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot();

        //when
        //then
        UnrecognizedTicketExpection exceptionMessage = assertThrows(UnrecognizedTicketExpection.class, () -> parkingLot.fetchCarListByCustomer(customer));
        assertEquals(UNRECOGNIZED_TICKET_EXCEPTION, exceptionMessage.getMessage());
    }

    @Test
    void should_throw_when_fetchCar_given_customer_with_not_issued_ticket() {
        //given
        Car anotherCar = new Car("car001");

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot anotherparkingLot = new ParkingLot();

        Customer customer = new Customer();

        anotherparkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(anotherCar)));

        //when
        //then
        UnrecognizedTicketExpection exceptionMessage = assertThrows(UnrecognizedTicketExpection.class, () -> parkingLot.fetchCarListByCustomer(customer));
        assertEquals(UNRECOGNIZED_TICKET_EXCEPTION, exceptionMessage.getMessage());
    }

    @Test
    void should_throw_when_fetchCar_given_customer_with_fetched_ticket() {
        //given
        Car targetCar = new Car("car001");

        ParkingLot parkingLot = new ParkingLot();

        Customer customer = new Customer();


        parkingLot.parkCars(customer, new ArrayList<>(Collections.singletonList(targetCar)));
        parkingLot.fetchCarListByCustomer(customer); // fetchedCarFirstTime

        //when
        //then
        UnrecognizedTicketExpection exceptionMessage = assertThrows(UnrecognizedTicketExpection.class, () -> parkingLot.fetchCarListByCustomer(customer));
        assertEquals(UNRECOGNIZED_TICKET_EXCEPTION, exceptionMessage.getMessage());
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
