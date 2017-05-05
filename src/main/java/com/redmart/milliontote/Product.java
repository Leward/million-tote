package com.redmart.milliontote;

import java.math.BigDecimal;

// TODO: Primitive obsession

/**
 * Represent a product.
 *
 * Object is composed of 6 integers. A Product instance takes at least 24 bytes.
 */
class Product {

    /**
     * ID to uniquely identify a product
     */
    private final Integer id;

    /**
     * Price of the product in cents
     */
    private final Integer price;

    /**
     * Length occupied by the product in centimeters
     */
    private final Integer length;

    /**
     * Width occupied by the product in centimeters
     */
    private final Integer width;

    /**
     * Height occupied by the product in centimeters
     */
    private final Integer height;

    /**
     * Weight of the product in grams
     */
    private final Integer weight;

    Product(Integer id, Integer price, Integer length, Integer width, Integer height, Integer weight) {
        this.id = id;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    /**
     * Simplification where the volume will only represent one dimension.
     */
    Product(Integer id, Integer price, Integer volume, Integer weight) {
        this(id, price, volume, 1, 1, weight);
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    /**
     * Volume occupied by the product in cubic centimeters
     */
    public Integer getVolume() {
        return length * height * width;
    }

    /**
     * Price per cubic centimeter in cents per cubic centimeters.
     */
    public BigDecimal getPricePerCubicCentimeter() {
        BigDecimal price = BigDecimal.valueOf(this.price);
        BigDecimal volume = BigDecimal.valueOf(getVolume());
        return price.divide(volume, 4); // 4 decimals is considered precise enough in our case
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
