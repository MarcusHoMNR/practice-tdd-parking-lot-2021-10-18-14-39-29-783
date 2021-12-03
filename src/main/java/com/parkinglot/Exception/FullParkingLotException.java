package com.parkinglot.Exception;

import static com.parkinglot.Exception.ExceptionConstant.FULL_PARKING_LOT_EXCEPTION;

public class FullParkingLotException extends RuntimeException{
    public FullParkingLotException() {
        super(FULL_PARKING_LOT_EXCEPTION);
    }
}
