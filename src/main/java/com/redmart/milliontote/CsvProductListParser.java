package com.redmart.milliontote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class CsvProductListParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvProductListParser.class);

    private final String productsFile;

    public CsvProductListParser(@Value("${redmart.products_list}") String productsFile) {
        this.productsFile = productsFile;
    }

    /**
     * Load the products from the CSV file as a {@link Stream}.
     */
    public Stream<Product> getProductStream() {
        Stream<String> lines = Stream.empty();
        try {
            lines = Files.lines(Paths.get(productsFile));
        } catch (IOException e) {
            LOGGER.error(String.format("Could not open file: %s", productsFile), e);
        }
        return lines.map(this::parseCsvLineAsProduct);
    }

    private Product parseCsvLineAsProduct(String line) {
        String[] fields = line.split(",");
        return new Product(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]),
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3]),
                Integer.parseInt(fields[4]),
                Integer.parseInt(fields[5])
        );
    }

}
