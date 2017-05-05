package com.redmart.milliontote;

import java.util.ArrayList;
import java.util.List;

public class Tote {

    /**
     * Capacity of the tote in cubic centimeters
     */
    private final Integer capacity;

    /**
     * Products contained in the tote
     */
    private List<Product> content = new ArrayList<>();

    public Tote(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Default Tote capacity
     */
    public Tote() {
        this(45 * 30 * 35);
    }

    public Integer getRemainingCapacity() {
        return capacity - getOccupiedCapacity();
    }

    public Integer getOccupiedCapacity() {
        return content.stream()
                .mapToInt(Product::getVolume)
                .sum();
    }

    public boolean canProductFit(Product product) {
        return product.getVolume() <= getRemainingCapacity();
    }

    public void addProduct(Product product) {
        if(content.contains(product)) {
            throw new DuplicateProductException();
        }
        if(!canProductFit(product)) {
            throw new ToteOverCapacityException();
        }
        content.add(product);
    }

    public Integer countItems() {
        return content.size();
    }

    public Integer getValue() {
        return content.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public Integer getWeight() {
        return content.stream()
                .mapToInt(Product::getWeight)
                .sum();
    }
}
