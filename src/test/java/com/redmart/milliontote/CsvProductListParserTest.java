package com.redmart.milliontote;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvProductListParserTest {

    @Test
    public void verify_first_parsed_lines() {
        CsvProductListParser parser = new CsvProductListParser("data/products.csv");
        // CSV Line: 1,556,24,14,15,1557
        List<Product> products = parser.getProductStream().collect(Collectors.toList());
        assertThat(products.get(0).getId()).isEqualTo(1);
        assertThat(products.get(0).getPrice()).isEqualTo(556);
        assertThat(products.get(0).getLength()).isEqualTo(24);
        assertThat(products.get(0).getWidth()).isEqualTo(14);
        assertThat(products.get(0).getHeight()).isEqualTo(15);
        assertThat(products.get(0).getWeight()).isEqualTo(1557);

        // CSV Line: 3,712,33,20,19,944
        assertThat(products.get(1).getId()).isEqualTo(3);
        assertThat(products.get(1).getPrice()).isEqualTo(712);
        assertThat(products.get(1).getLength()).isEqualTo(33);
        assertThat(products.get(1).getWidth()).isEqualTo(20);
        assertThat(products.get(1).getHeight()).isEqualTo(19);
        assertThat(products.get(1).getWeight()).isEqualTo(944);
    }

}