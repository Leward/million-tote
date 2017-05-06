package com.redmart.milliontote;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KnapsackDynamicProgrammingStrategyTest {

    /**
     * Youtube video: https://www.youtube.com/watch?v=8LusJS5-AGo
     */
    @Test
    public void should_find_same_result_as_example_from_youtube() {
        List<Product> products = new ArrayList<>(4);
        products.add(new Product(1, 1, 1, 0));
        products.add(new Product(2, 4, 3, 0));
        products.add(new Product(3, 5, 4, 0));
        products.add(new Product(4, 7, 5, 0));

        KnapsackDynamicProgrammingStrategy dynamicProgrammingStrategy = new KnapsackDynamicProgrammingStrategy();
        Tote bestTote = dynamicProgrammingStrategy.getBestTote(products, 7);

        assertThat(bestTote.countItems()).isEqualTo(2);
        assertThat(bestTote.getValue()).isEqualTo(9);
        assertThat(bestTote.getOccupiedCapacity()).isEqualTo(7);
    }

    @Test
    public void test_with_data_from_rosetta_code() {
        List<Product> products = new ArrayList<>(4);
        products.add(new Product(1, 150, 9, 0));
        products.add(new Product(2, 35, 13, 0));
        products.add(new Product(3, 200, 153, 0));
        products.add(new Product(4, 160, 50, 0));
        products.add(new Product(5, 60, 15, 0));
        products.add(new Product(6, 45, 68, 0));
        products.add(new Product(7, 60, 27, 0));
        products.add(new Product(8, 40, 39, 0));
        products.add(new Product(9, 30, 23, 0));
        products.add(new Product(10, 10, 52, 0));
        products.add(new Product(11, 70, 11, 0));
        products.add(new Product(12, 30, 32, 0));
        products.add(new Product(13, 15, 24, 0));
        products.add(new Product(14, 10, 48, 0));
        products.add(new Product(15, 40, 73, 0));
        products.add(new Product(16, 70, 42, 0));
        products.add(new Product(17, 75, 43, 0));
        products.add(new Product(18, 80, 22, 0));
        products.add(new Product(19, 20, 7, 0));
        products.add(new Product(20, 12, 18, 0));
        products.add(new Product(21, 50, 4, 0));
        products.add(new Product(22, 10, 30, 0));

        KnapsackDynamicProgrammingStrategy dynamicProgrammingStrategy = new KnapsackDynamicProgrammingStrategy();
        Tote bestTote = dynamicProgrammingStrategy.getBestTote(products, 400);

        assertThat(bestTote.countItems()).isEqualTo(12);
        assertThat(bestTote.getValue()).isEqualTo(1030);
        assertThat(bestTote.getOccupiedCapacity()).isEqualTo(396);
    }

}