package com.parkinglot;

import java.util.List;

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

}
