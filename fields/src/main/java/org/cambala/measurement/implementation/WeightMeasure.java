package org.cambala.measurement.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cambala.measurement.Measure;

@Getter
@AllArgsConstructor
public enum WeightMeasure implements Measure {

    @JsonProperty("MILLIGRAM") MILLIGRAM("мг"),
    @JsonProperty("GRAM") GRAM("г"),
    @JsonProperty("KILOGRAM") KILOGRAM("кг"),
    @JsonProperty("TON") TON("т");

    private final String shortName;
}
