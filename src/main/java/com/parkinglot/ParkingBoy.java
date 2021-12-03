package com.parkinglot;

import com.parkinglot.Exception.FullParkingLotException;
import com.parkinglot.Exception.UnrecognizedTicketExpection;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    final List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void parkCars(Customer customer, List<Car> carList) {
        ParkingLot targetLot = parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getCapacity() > parkingLot.getParkedCarList().size())
                .findFirst().orElse(null);
        if (targetLot != null) {
            targetLot.parkCars(customer, carList);
        } else {
            throw new FullParkingLotException();
        }
    }

    public List<Car> fetchCars(Customer customer) {
        if (customer.getTicketList().isEmpty()) {
            throw new UnrecognizedTicketExpection();
        }
        for (Ticket ticket : customer.getTicketList()) {
            if (ticket.isFetched()) {
                throw new UnrecognizedTicketExpection();
            }
        }
        ParkingLot targetParkingLot = parkingLotList.stream()
                .filter(
                        parkingLot ->
                                parkingLot.getParkedCarList().stream().map(Car::getCarId).collect(Collectors.toList()).contains(
                                        customer.getTicketList().get(0).getLinkedCarId()
                                )
                ).findAny().get();
        return targetParkingLot.fetchCarListByCustomer(customer);
    }
}
