package com.parkinglot;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    final List<ParkingLot> parkingLotList;
    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void parkCars(Customer customer, List<Car> carList) {
        parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getCapacity() >= parkingLot.getParkedCarList().size())
                .findFirst().get()
                .parkCars(customer, carList);
    }
}
