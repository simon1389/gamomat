package com.gamomat.gamomatbe.exceptions;

public class WinLineLengthMismatchException extends Exception {
    public WinLineLengthMismatchException(int expectedLength) {
        super("At least one Winline has a mismatch in its length. Expected: " + expectedLength);
    }
}
