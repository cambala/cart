package org.cambala.fields.implementation;

import lombok.NoArgsConstructor;
import org.cambala.aop.Field;
import org.cambala.fields.BaseField;
import org.cambala.measurement.Measure;
import org.cambala.utils.NumericFieldValueFormatter;
import org.cambala.values.NumericValue;

import java.text.DecimalFormat;

@NoArgsConstructor
@Field(type = "NUMERIC")
public final class NumericField extends BaseField<Double> {

    private static final DecimalFormat FORMATTER = NumericFieldValueFormatter.FORMATTER_3;

    public NumericField(String name, String value, Measure measure) {
        super(name, measure);
        this.value = new NumericValue(Double.valueOf(value));
    }

    public NumericField(String value) {
        super();
        this.value = new NumericValue(Double.valueOf(value));
    }

    public Double getValue() {
        return value.getValue();
    }

    @Override
    public String description() {
        return String.format("%s: %s %s", getName(), FORMATTER.format(getValue()), getMeasure().getShortName());
    }

    @Override
    public String detailedDescription() {
        return description();
    }
}
