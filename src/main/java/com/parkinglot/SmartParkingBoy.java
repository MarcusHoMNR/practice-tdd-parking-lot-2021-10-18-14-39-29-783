package com.parkinglot;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public void parkCars(Customer customer, List<Car> carList) {
        parkingLotList.stream().max(comparingInt(ParkingLot::getRemainingEmptySpacesNumber))
                .get()
                .parkCars(customer, carList);
    }
}
