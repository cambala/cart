package org.cambala.fields;

import org.cambala.measurement.Measure;

import java.io.Serializable;

public interface Field extends Serializable, Described {

    String getName();
    FieldType getType();
    Measure getMeasure();
    Object getValue();
}
