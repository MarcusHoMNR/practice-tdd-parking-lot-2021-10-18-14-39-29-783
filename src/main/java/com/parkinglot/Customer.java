package com.parkinglot;

import java.util.List;

public class Customer {
    private final List<Ticket> ticketList;

    public Customer(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
