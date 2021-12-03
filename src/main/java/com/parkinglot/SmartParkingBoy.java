package com.parkinglot;

import com.parkinglot.Exception.UnrecognizedTicketExpection;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class SmartParkingBoy {
    final List<ParkingLot> parkingLotList;

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void parkCars(Customer customer, List<Car> carList) {
        parkingLotList.stream().max(comparingInt(ParkingLot::getRemainingEmptySpacesNumber))
                .get()
                .parkCars(customer, carList);
    }

    public List<Car> fetchCars(Customer customer) {
        if (customer.getTicketList().isEmpty()) {
            throw new UnrecognizedTicketExpection();
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
