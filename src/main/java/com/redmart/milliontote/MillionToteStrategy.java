package com.redmart.milliontote;

import java.util.List;


public interface MillionToteStrategy {

    /**
     * Get the best possible Tote.
     * A restriction is to only pick a given product once.
     * @param products all the available products that can be used to fill the tote
     * @param toteVolume volume of the tote in cubic centimeters
     * @return the best possible Tote according the implemented strategy
     */
    Tote getBestTote(List<Product> products, Integer toteVolume);

}
