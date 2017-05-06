package com.redmart.milliontote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Maximize the tote value using 0/1 Knapsack Dynamic Programming algorithm.
 * This should give the best possible Tote <strong>but is quite expensive in memory. </strong></p>
 * Implementation based on:
 * <ul>
 * <li>Wikipedia article: https://en.wikipedia.org/wiki/Knapsack_problem#0.2F1_knapsack_problem</li>
 * <li>Youtube explanation video: https://www.youtube.com/watch?v=8LusJS5-AGo</li>
 * </ul>
 */
public class KnapsackDynamicProgrammingStrategy implements MillionToteStrategy {

    public static final Logger LOGGER = LoggerFactory.getLogger(KnapsackDynamicProgrammingStrategy.class);

    @Override
    public Tote getBestTote(List<Product> products, Integer toteVolume) {
        // Each row (i) of the matrix represent a product
        // Each column (j) is  a possible capacity from 0 to the toteVolume
        LOGGER.info("Matrix size is {} integers", products.size() * (toteVolume + 1));
        int[][] matrix = new int[products.size()][toteVolume + 1];

        products.sort(Comparator.comparing(Product::getWeight));
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            for (int j = 0; j < toteVolume + 1; j++) {
                int previousValue = getPreviousValueInMatrix(i, j, matrix);
                // The product does not fit under the current capacity, keep the previous value
                if (product.getVolume() > j) {
                    matrix[i][j] = previousValue;
                } else {
                    int toteRemainingCapacity = j - product.getVolume();
                    int previousValueForRemainigCapacity = getPreviousValueInMatrix(i, toteRemainingCapacity, matrix);
                    matrix[i][j] = max(previousValue, product.getPrice() + previousValueForRemainigCapacity);
                }
            }
        }

        Tote tote = new Tote(toteVolume);

        // Use the matrix to fill the tote
        int i = matrix.length - 1;
        int j = toteVolume;
        while (i >= 0 && j > 0) {
            if (doesValueOriginatesFromLine(i, j, matrix) && matrix[i][j] > 0) {
                Product product = products.get(i);
                tote.addProduct(product);
                i--;
                j -= product.getVolume();
            } else {
                i--;
            }
        }

        return tote;
    }

    private int getPreviousValueInMatrix(int i, int j, int[][] matrix) {
        if (i == 0) {
            return 0;
        } else {
            return matrix[i - 1][j];
        }
    }

    private int max(int... values) {
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    private boolean doesValueOriginatesFromLine(int i, int j, int[][] matrix) {
        return i == 0 || matrix[i - 1][j] != matrix[i][j];
    }
}
