package com.redmart.milliontote;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class KnapsackDynamicProgrammingStrategyTest {

    /**
     * Youtube video: https://www.youtube.com/watch?v=8LusJS5-AGo
     */
    @Test
    public void should_find_same_result_as_example_from_youtube() {
        List<Product> products = new ArrayList<>(4);
        products.add(new Product(1, 1, 1, 1, 1, 0));
        products.add(new Product(2, 4, 3, 1, 1, 0));
        products.add(new Product(3, 5, 4, 1, 1, 0));
        products.add(new Product(4, 7, 5, 1, 1, 0));

        KnapsackDynamicProgrammingStrategy dynamicProgrammingStrategy = new KnapsackDynamicProgrammingStrategy();
        Tote bestTote = dynamicProgrammingStrategy.getBestTote(products, 7);

        assertThat(bestTote.countItems()).isEqualTo(2);
        assertThat(bestTote.getValue()).isEqualTo(9);
        assertThat(bestTote.getOccupiedCapacity()).isEqualTo(7);
    }

}