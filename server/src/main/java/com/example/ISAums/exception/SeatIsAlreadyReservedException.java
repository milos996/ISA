package com.example.ISAums.exception;

public class SeatIsAlreadyReservedException extends CustomException {
    public SeatIsAlreadyReservedException(String message, int segment, int column, int row) {
        super(message + "(" + segment + ", " + row + ", " + column + ")." );
    }
}
