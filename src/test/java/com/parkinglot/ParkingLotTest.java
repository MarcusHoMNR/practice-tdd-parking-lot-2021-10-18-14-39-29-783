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
        assertEquals(0, customer.getTicketList().size());
    }
}
