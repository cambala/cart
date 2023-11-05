package org.cambala.measurement.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cambala.measurement.Measure;

@Getter
@AllArgsConstructor
public enum SizeMeasure implements Measure {

    @JsonProperty("MILLIMETER") MILLIMETER("мм"),
    @JsonProperty("CENTIMETER") CENTIMETER("см"),
    @JsonProperty("METER") METER("м"),
    @JsonProperty("KILOMETER") KILOMETER("км");

    private final String shortName;
}
