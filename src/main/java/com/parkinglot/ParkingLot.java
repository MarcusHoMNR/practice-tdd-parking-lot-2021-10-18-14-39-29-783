package com.parkinglot;

import com.parkinglot.Exception.FullParkingLotException;
import com.parkinglot.Exception.UnrecognizedTicketExpection;

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

    public void parkCars(Customer customer, List<Car> carList) {
        for (Car car : carList) {
            parkCar(customer, car);
        }
    }

    private void parkCar(Customer customer, Car car) {
        assignParkingLot(car);
        Ticket ticket = issueTicket(car);
        assignTicketToCustomer(customer, ticket);
    }

    private void assignParkingLot(Car car) {
        parkedCarList.add(car);
    }


    private Ticket issueTicket(Car car) {
        if (capacity >= parkedCarList.size()) {
            return new Ticket(car.getCarId());
        }
        throw new FullParkingLotException();
    }

    private void assignTicketToCustomer(Customer customer, Ticket ticket) {
        if (ticket == null) {
            return;
        }
        customer.setTicketListBySingleTicket(ticket);
    }

    public List<Car> fetchCarListByCustomer(Customer customer) {
        validateTicket(customer);


        List<Car> fetchedCarList = new ArrayList<>();


        for (Ticket ticket : customer.getTicketList()) {
            fetchedCarList.add(fetchCar(ticket));
        }

        return fetchedCarList;
    }

    private void validateTicket(Customer customer) {
        if (customer.getTicketList().isEmpty()) {
            throw new UnrecognizedTicketExpection();
        }
        if (customer.getTicketList().get(0).isFetched()) {
            throw new UnrecognizedTicketExpection();
        }
    }

    private Car fetchCar(Ticket ticket) {
        Car fetchedCar = parkedCarList.stream().filter(car -> car.getCarId().equals(ticket.getLinkedCarId())).findFirst().orElse(null);
        if (fetchedCar == null) {
            throw new UnrecognizedTicketExpection();
        }
        ticket.setFetched(true);
        parkedCarList.remove(fetchedCar);
        return fetchedCar;
    }
}
