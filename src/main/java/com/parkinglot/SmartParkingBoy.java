package com.parkinglot;

import java.util.List;

public class SmartParkingBoy {
    final List<ParkingLot> parkingLotList;
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void parkCars(Customer customer, List<Car> carList) {}
}
