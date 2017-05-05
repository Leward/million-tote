package com.redmart.milliontote;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {
    @Test
    public void should_calculate_volume() throws Exception {
        Product product = new Product(1, 1, 10, 5, 2, 0);
        assertThat(product.getVolume()).isEqualTo(100);
    }

    @Test
    public void getPricePerCubicCentimeter() throws Exception {
        Product product = new Product(1, 1000, 10, 5, 2, 0);
        assertThat(product.getPricePerCubicCentimeter()).isEqualTo(BigDecimal.valueOf(10));
    }

}