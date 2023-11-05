package org.cambala.values;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumericValue implements Value<Double> {

    Double value;
}
