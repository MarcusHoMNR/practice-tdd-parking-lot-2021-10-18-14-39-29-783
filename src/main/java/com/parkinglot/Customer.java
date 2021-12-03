package com.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Customer {
    private final List<Ticket> ticketList;

    public Customer(Ticket ticket) {
        this.ticketList = new ArrayList<>(Collections.singletonList(ticket));
    }


    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketListBySingleTicket(Ticket ticket) {
        ticketList.add(ticket);
    }
}
