package org.cambala;


import org.cambala.fields.Field;
import org.cambala.fields.implementation.NumericField;
import org.cambala.measurement.implementation.UnitMeasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptionTest {

    @Test
    void shouldReturnFormattedDescription() {
        Field field = new NumericField("Количество", "10", UnitMeasure.UNIT);
        assertEquals("Количество: 10 шт", field.description());
    }

}