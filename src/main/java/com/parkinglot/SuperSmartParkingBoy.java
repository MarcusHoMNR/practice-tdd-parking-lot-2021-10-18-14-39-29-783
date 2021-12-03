package com.parkinglot;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }
    @Override
    public void parkCars(Customer customer, List<Car> carList) {
        parkingLotList.stream().max(comparingInt(ParkingLot::getRemainingEmptySpacesRate))
                .get()
                .parkCars(customer, carList);
    }
}
