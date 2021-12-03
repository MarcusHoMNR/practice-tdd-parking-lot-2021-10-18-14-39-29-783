package com.parkinglot;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    final List<ParkingLot> parkingLotList;
    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void parkCars(Customer customer, List<Car> carList) {
        parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getCapacity() > parkingLot.getParkedCarList().size())
                .findFirst().get()
                .parkCars(customer, carList);
    }

    public List<Car> fetchCars(Customer customer) {
        if (customer.getTicketList().isEmpty()) {
            return parkingLotList.get(0).fetchCarListByCustomer(customer);
        }
        for (Ticket ticket: customer.getTicketList()) {
            if (ticket.isFetched()) {
                return parkingLotList.get(0).fetchCarListByCustomer(customer);
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
