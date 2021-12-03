package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final List<Car> parkedCarList;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCarList = new ArrayList<>();
    }

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
        this.parkedCarList = new ArrayList<>();
    }

    public Customer parkCar(Car car) {
        boolean isAssignLocationSuccess = assignLocation(car);
        Ticket ticket = null;
        if (isAssignLocationSuccess) {
            ticket = issueTicket(car);
        }

        Customer customerWithParkedTicket = assignTicketToCustomer(ticket);

        return customerWithParkedTicket;
    }

    private boolean assignLocation(Car car) {
        return parkedCarList.add(car);
    }


    private Ticket issueTicket(Car car) {
        return new Ticket(car.getCarId());
    }

    private Customer assignTicketToCustomer(Ticket ticket) {
        return new Customer(ticket);
    }


}
