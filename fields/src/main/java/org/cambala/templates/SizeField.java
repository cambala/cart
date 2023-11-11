package org.cambala.templates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.exception.ApplicationException;
import org.cambala.fields.implementation.NumericField;
import org.cambala.measurement.implementation.SizeMeasure;
import org.cambala.utils.NumericFieldValueFormatter;

import java.text.DecimalFormat;

@Getter
@NoArgsConstructor
@Template(templateId = "SIZE", templateName = "Размер")
public class SizeField extends AbstractTemplate {

    private static final DecimalFormat FORMATTER = NumericFieldValueFormatter.FORMATTER_3;

    private NumericField width;
    private NumericField height;
    private NumericField depth;
    private SizeMeasure measure;

    public SizeField(String width, String height, String depth, SizeMeasure measure) {
        this.width = new NumericField(width);
        this.depth = new NumericField(depth);
        this.height = new NumericField(height);
        this.measure = measure;
    }

    public void setWidth(NumericField width) {
        if (width.getValue() < 0) {
            throw new ApplicationException("Значение ширины не может быть меньше нуля", 400);
        }
        this.width = width;
    }

    public void setHeight(NumericField height) {
        this.height = height;
    }

    public void setDepth(NumericField depth) {
        this.depth = depth;
    }

    public void setMeasure(SizeMeasure measure) {
        this.measure = measure;
    }

    public String description() {
        return String.format("%s x %s x %s %s.",
                FORMATTER.format(this.getWidth()),
                FORMATTER.format(this.getHeight()),
                FORMATTER.format(this.getDepth()),
                this.measure.getShortName()
        );
    }

    @Override
    public String detailedDescription() {
        return String.format("Ширина: %s %s, Высота: %s %s, Глубина: %s %s",
                FORMATTER.format(this.getWidth()), this.measure.getDottedShortName(),
                FORMATTER.format(this.getHeight()), this.measure.getDottedShortName(),
                FORMATTER.format(this.getDepth()), this.measure.getDottedShortName()
        );
    }

    @Override
    public String toString() {
        return description();
    }
}
