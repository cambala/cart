package org.cambala.cart.configuration.mongo;


import lombok.extern.slf4j.Slf4j;
import org.cambala.cart.configuration.mongo.conversion.MeasurementReadingConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
@Slf4j
public class MongoConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(
                Arrays.asList(new MeasurementReadingConverter())
        );
    }
}



