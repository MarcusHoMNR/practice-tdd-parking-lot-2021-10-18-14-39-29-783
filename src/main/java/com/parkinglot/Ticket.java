package com.parkinglot;

public class Ticket {
    private boolean isFetched;
    private final String linkedCarId;

    public Ticket(String linkedCarId) {
        this.isFetched = false;
        this.linkedCarId = linkedCarId;
    }

    public String getLinkedCarId() {
        return linkedCarId;
    }

    public boolean isFetched() {
        return isFetched;
    }

    public void setFetched(boolean fetched) {
        isFetched = fetched;
    }
}
