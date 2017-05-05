package com.redmart.milliontote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MillionToteApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MillionToteApplication.class);

    private final CsvProductListParser csvProductListParser;

    @Autowired
    public MillionToteApplication(CsvProductListParser csvProductListParser) {
        this.csvProductListParser = csvProductListParser;
    }

    public static void main(String[] args) {
        // Start a Spring Application
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(MillionToteApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        MillionToteApplication app = context.getBean(MillionToteApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        int toteVolume = 45 * 30 * 35;
        LOGGER.info("Using Tote having a volume of {} cubic centimeters", toteVolume);

        List<Product> products = csvProductListParser.getProductStream().collect(Collectors.toList());
        products.sort(Comparator.comparing(Product::getVolume).reversed());
        LOGGER.info("{} products loaded", products.size());

        MillionToteStrategy highPriceDensityStrategy = new HighPriceDensityStrategy();
        Tote firstTote = highPriceDensityStrategy.getBestTote(products, toteVolume);
        System.out.println("Using the HighPriceDensityStrategy: ");
        System.out.printf("Tote filled with %d items, for a value of %d cents and weighting %d grams. \n",
                firstTote.countItems(),
                firstTote.getValue(),
                firstTote.getWeight());
        System.out.printf("%d cubic centimeters are still available in the tote\n\n", firstTote.getRemainingCapacity());

        MillionToteStrategy dynamicProgrammingStrategy = new KnapsackDynamicProgrammingStrategy();
        Tote secondTote = dynamicProgrammingStrategy.getBestTote(products, toteVolume);
        System.out.println("Using the HighPriceDensityStrategy: ");
        System.out.printf("Tote filled with %d items, for a value of %d cents and weighting %d grams. \n",
                secondTote.countItems(),
                secondTote.getValue(),
                secondTote.getWeight());
        System.out.printf("%d cubic centimeters are still available in the tote\n\n", secondTote.getRemainingCapacity());
    }
}
