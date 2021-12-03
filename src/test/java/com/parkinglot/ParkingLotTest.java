package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_customer_with_ticket_when_parkCar_given_parking_lot_And_Car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("car001");

        //when
        Customer customer = parkingLot.parkCar(car);

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

        //when
        Customer customer = parkingLot.parkCar(car);

        //then
        assertNull(customer.getTicketList());
    }

    @Test
    void should_return_target_car_when_fetchCar_given_customer_with_one_valid_ticket() {
        //given

        Car targetCar = new Car("car001");
        Ticket ticket = new Ticket(targetCar.getCarId());
        Customer customer = new Customer(ticket);
        ParkingLot parkingLot = new ParkingLot();

        //when
        Customer parkedCustomer = parkingLot.parkCar(targetCar);
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
}
