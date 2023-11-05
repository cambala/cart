package org.cambala.fields;

import org.cambala.measurement.Measure;
import org.cambala.values.Value;

public abstract class BaseField<T> implements Field {

    protected String name;
    protected FieldType type;
    protected Measure measure;
    protected Value<T> value;

    protected BaseField(String name, Measure measure) {
        org.cambala.aop.Field annotation = this.getClass().getAnnotation(org.cambala.aop.Field.class);
        this.name = name.strip();
        this.type = FieldType.valueOf(annotation.type());
        this.measure = measure;
    }

    protected BaseField() {
        org.cambala.aop.Field annotation = this.getClass().getAnnotation(org.cambala.aop.Field.class);
        this.type = FieldType.valueOf(annotation.type());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public FieldType getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = FieldType.valueOf(type);
    }

    @Override
    public Measure getMeasure() {
        return this.measure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Value<T> value) {
        this.value = value;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
