package org.cambala.fields;

public interface Described {

    String description();

    default String detailedDescription() {
        return description();
    };
}
