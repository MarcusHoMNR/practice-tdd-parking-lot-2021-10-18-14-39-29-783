package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        assignParkingLot(car);
        Ticket ticket = issueTicket(car);

        return assignTicketToCustomer(ticket);
    }

    private void assignParkingLot(Car car) {
        parkedCarList.add(car);
    }


    private Ticket issueTicket(Car car) {
        if (capacity >= parkedCarList.size()) {
            return new Ticket(car.getCarId());
        }
        return null;
    }

    private Customer assignTicketToCustomer(Ticket ticket) {
        if (ticket == null) {
            return new Customer();
        }
        return new Customer(ticket);
    }


    public Car fetchCarByCustomer(Customer customer) {
        validateTicket(customer);

        Car fetchedCar = null;


        for (Ticket ticket : customer.getTicketList()) {
            fetchedCar = fetchCar(ticket);
        }

        return fetchedCar;
    }

    private void validateTicket(Customer customer) {

    }

    private Car fetchCar(Ticket ticket) {
        return parkedCarList.stream().filter(car -> car.getCarId().equals(ticket.getLinkedCarId())).findFirst().orElse(null);
    }
}
