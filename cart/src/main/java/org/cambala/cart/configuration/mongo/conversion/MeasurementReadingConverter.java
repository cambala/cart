package org.cambala.cart.configuration.mongo.conversion;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.cambala.aop.scanner.FieldsScanner;
import org.cambala.measurement.Measure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ReadingConverter
public class MeasurementReadingConverter implements Converter<String, Measure> {

    @Override
    public Measure convert(@Nonnull String value) {
        return FieldsScanner.getMeasureInstance(value);
    }
}
