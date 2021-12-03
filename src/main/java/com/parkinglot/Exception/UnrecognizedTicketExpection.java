package com.parkinglot.Exception;
import static com.parkinglot.Exception.ExceptionConstant.UNRECOGNIZED_TICKET_EXCEPTION;

public class UnrecognizedTicketExpection extends RuntimeException{
    public UnrecognizedTicketExpection() {
        super(UNRECOGNIZED_TICKET_EXCEPTION);
    }
}
