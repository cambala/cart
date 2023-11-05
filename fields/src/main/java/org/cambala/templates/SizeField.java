package org.cambala.templates;

import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.fields.implementation.NumericField;
import org.cambala.measurement.implementation.SizeMeasure;
import org.cambala.utils.NumericFieldValueFormatter;

import java.text.DecimalFormat;

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
        this.width.setName("Ширина");
        this.width.setMeasure(measure);

        this.depth = new NumericField(depth);
        this.depth.setName("Глубина");
        this.depth.setMeasure(measure);

        this.height = new NumericField(height);
        this.height.setName("Высота");
        this.height.setMeasure(measure);

        this.measure = measure;
    }

    public Double getWidth() {
        return width.getValue();
    }

    public Double getHeight() {
        return height.getValue();
    }

    public Double getDepth() {
        return depth.getValue();
    }

    public SizeMeasure getMeasure() {
        return measure;
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
