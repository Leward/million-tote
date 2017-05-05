package com.redmart.milliontote;

import java.util.Comparator;
import java.util.List;

/**
 * <p>This strategy tries to maximize the tote value by picking the products with the best ratio
 * value / volume first.</p>
 *
 * <p>This does not guarantee the best result, but has a fast execution time and low memory consumption.</p>
 */
public class HighPriceDensityStrategy implements MillionToteStrategy {
    @Override
    public Tote getBestTote(List<Product> products, Integer toteVolume) {
        Comparator<Product> wieghtAscendingComparator = Comparator.comparing(Product::getWeight);
        Comparator<Product> priceDensityDescendingComparator = Comparator.comparing(Product::getPricePerCubicCentimeter).reversed();
        products.sort(priceDensityDescendingComparator.thenComparing(wieghtAscendingComparator));
        Tote tote = new Tote(toteVolume);
        products.forEach(product -> {
            if(tote.canProductFit(product)) {
                tote.addProduct(product);
            }
        });
        return tote;
    }

}
