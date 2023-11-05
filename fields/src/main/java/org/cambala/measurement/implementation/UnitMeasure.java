package org.cambala.measurement.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cambala.measurement.Measure;

@Getter
@AllArgsConstructor
public enum UnitMeasure implements Measure {

    @JsonProperty("MILLIMETER") UNIT("шт");

    private final String shortName;
}
