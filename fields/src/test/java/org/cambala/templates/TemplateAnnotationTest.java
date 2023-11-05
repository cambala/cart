package org.cambala.templates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateAnnotationTest {

    @Test
    void shouldCreateInstanceWithAnnotationValues() {
        TitleField field = new TitleField("some title");
        assertEquals("some title", field.getTitle());
        assertEquals("TITLE", field.getTemplateId());
        assertEquals("Наименование", field.getTemplateName());
    }
}
