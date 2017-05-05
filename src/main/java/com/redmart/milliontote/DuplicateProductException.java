package com.redmart.milliontote;

public class DuplicateProductException extends RuntimeException {

    public DuplicateProductException() {
        super("Cannot add twice the same product in a tote.");
    }

}
