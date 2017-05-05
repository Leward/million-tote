package com.redmart.milliontote;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ToteTest {

    private static final Product PRODUCT_1 = new Product(1, 100, 22, 1);
    private static final Product PRODUCT_2 = new Product(2, 20, 44, 8);
    private static final Product PRODUCT_3 = new Product(3, 5, 50, 4);

    @Test
    public void getRemainingCapacity() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
    }

    @Test
    public void should_not_be_able_to_add_several_times_the_same_product() {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        Throwable throwable = catchThrowable(() -> tote.addProduct(PRODUCT_1));
        assertThat(throwable).isInstanceOf(DuplicateProductException.class);
    }

    @Test
    public void should_not_be_able_to_add_a_product_that_does_not_fit_in_the_tote() {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        Throwable throwable = catchThrowable(() -> tote.addProduct(PRODUCT_3));
        assertThat(throwable).isInstanceOf(ToteOverCapacityException.class);
    }

    @Test
    public void should_calculate_occupied_capacity() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.getOccupiedCapacity()).isEqualTo(66);
    }

    @Test public void should_calculate_remaining_capacity() {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.getRemainingCapacity()).isEqualTo(34);
    }

    @Test
    public void product_should_fit() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        assertThat(tote.canProductFit(PRODUCT_2)).isTrue();
    }

    @Test
    public void product_should_not_fit() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.canProductFit(PRODUCT_3)).isFalse();
    }

    @Test
    public void should_count_items_in_the_tote() throws Exception {
        Tote tote = new Tote(100);
        assertThat(tote.countItems()).isEqualTo(0);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.countItems()).isEqualTo(1);
    }

    @Test
    public void should_calculate_value() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.getValue()).isEqualTo(120);
    }

    @Test
    public void should_calculate_wight() throws Exception {
        Tote tote = new Tote(100);
        tote.addProduct(PRODUCT_1);
        tote.addProduct(PRODUCT_2);
        assertThat(tote.getWeight()).isEqualTo(9);
    }

}