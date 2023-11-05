package org.cambala.templates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionFieldTest {

    private final DescriptionField descriptionField = new DescriptionField("some text");


    @Test
    void shouldReturnCorrectFieldDescription() {
        assertEquals("Описание: some text", descriptionField.description());
        assertEquals(descriptionField.description(), descriptionField.detailedDescription());
    }

}