package org.cambala.values;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TextValue implements Value<String> {

    String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
