package com.parkinglot;

public class Car {
    private final String carId;
    private int occupiedPosition;

    public Car(String carId) {
        this.carId = carId;
    }

    public void setOccupiedPosition(int occupiedPosition) {
        this.occupiedPosition = occupiedPosition;
    }

    public String getCarId() {
        return carId;
    }
}
