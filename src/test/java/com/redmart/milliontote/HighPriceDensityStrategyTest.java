package com.redmart.milliontote;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HighPriceDensityStrategyTest {

    /**
     * Youtube video: https://www.youtube.com/watch?v=8LusJS5-AGo
     * Demonstrate that the algorithm is not optimal
     */
    @Test
    public void should_not_find_same_result_as_example_from_youtube() {
        List<Product> products = new ArrayList<>(4);
        products.add(new Product(1, 1, 1, 0));
        products.add(new Product(2, 4, 3, 0));
        products.add(new Product(3, 5, 4, 0));
        products.add(new Product(4, 7, 5, 0));

        HighPriceDensityStrategy densityStrategy = new HighPriceDensityStrategy();
        Tote bestTote = densityStrategy.getBestTote(products, 7);

        assertThat(bestTote.getValue()).isNotEqualTo(9);
    }

}