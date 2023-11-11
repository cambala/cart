package org.cambala.fields;

import org.cambala.measurement.Measure;
import org.cambala.values.Value;

public abstract class BaseField<T> extends Field<T> {

    protected BaseField() {
        org.cambala.aop.Field annotation = this.getClass().getAnnotation(org.cambala.aop.Field.class);
        this.type = FieldType.valueOf(annotation.type());
    }

    protected BaseField(String name, Value<T> value) {
        this.name = name;
        this.value = value;
    }

    protected BaseField(String name, Value<T> value, Measure measure) {
        this.name = name;
        this.value = value;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }
}
