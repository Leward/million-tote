package com.redmart.milliontote;

public class ToteOverCapacityException extends RuntimeException {

    public ToteOverCapacityException() {
        super("The tote is over its maximum capacity");
    }

}
