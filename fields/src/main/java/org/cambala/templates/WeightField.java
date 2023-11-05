package org.cambala.templates;

import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.fields.implementation.NumericField;
import org.cambala.measurement.Measure;
import org.cambala.utils.NumericFieldValueFormatter;

import java.text.DecimalFormat;

@NoArgsConstructor
@Template(templateId = "WEIGHT", templateName = "Вес")
public final class WeightField extends AbstractTemplate {

    private static final DecimalFormat FORMATTER = NumericFieldValueFormatter.FORMATTER_3;

    private NumericField weight;
    private Measure measure;

    public WeightField(String weight, Measure measure) {
        this.weight = new NumericField(this.getTemplateName(), weight, measure);
        this.measure = measure;
    }

    public Double getWeight() {
        return weight.getValue();
    }

    public String description() {
        return String.format("%s: %s %s.",
                weight.getName(),
                FORMATTER.format(this.getWeight()),
                this.measure.getShortName()
        );
    }

    @Override
    public String toString() {
        return description();
    }
}
