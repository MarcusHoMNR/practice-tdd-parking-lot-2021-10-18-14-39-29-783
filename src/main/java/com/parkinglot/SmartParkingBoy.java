package com.parkinglot;

import com.parkinglot.Exception.UnrecognizedTicketExpection;

import java.util.ArrayList;
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
        return new ArrayList<>();
    }

}
