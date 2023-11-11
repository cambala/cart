package org.cambala.fields.implementation;

import lombok.NoArgsConstructor;
import org.cambala.aop.Field;
import org.cambala.fields.BaseField;
import org.cambala.values.TextValue;


@NoArgsConstructor
@Field(type = "TEXT")
public final class TextField extends BaseField<String> {

    public TextField(String name, String value) {
        super(name, new TextValue(value.strip()));
    }

    public TextField(String value) {
        super(null, new TextValue(value.strip()));
    }

    public void setValue(TextValue value) {
        this.value = value;
    }

    public String getValue() {
        return value.getValue();
    }

    @Override
    public String description() {
        return String.format("%s: %s", name, getValue());
    }

    @Override
    public String detailedDescription() {
        return description();
    }
}
