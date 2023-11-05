package org.cambala.fields.implementation;

import lombok.NoArgsConstructor;
import org.cambala.aop.Field;
import org.cambala.fields.BaseField;
import org.cambala.values.TextValue;


@Field(type = "TEXT")
@NoArgsConstructor
public final class TextField extends BaseField<String> {

    public TextField(String name, String value) {
        super(name, null);
        this.value = new TextValue(value.strip());
    }

    public TextField(String name, TextValue value) {
        super(name, null);
        this.value = value;
    }

    public String name() {
        return this.name;
    }

    public String getValue() {
        return value.getValue();
    }

    @Override
    public String description() {
        return String.format("%s: %s", getName(), getValue());
    }

    @Override
    public String detailedDescription() {
        return description();
    }
}
