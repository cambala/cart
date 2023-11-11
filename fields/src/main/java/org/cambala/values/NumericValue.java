package org.cambala.values;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NumericValue implements Value<Double> {

    Double value;

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}
