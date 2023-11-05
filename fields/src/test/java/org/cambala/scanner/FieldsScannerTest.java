package org.cambala.scanner;

import org.cambala.aop.scanner.FieldsScanner;
import org.cambala.templates.DescriptionField;
import org.cambala.templates.SizeField;
import org.cambala.templates.TitleField;
import org.cambala.templates.WeightField;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldsScannerTest {

    @Test
    void shouldCollectMapOfTemplatesByTheirIds() {
        Map<String, Class> parsedTemplates = FieldsScanner.getTemplates();

        assertTrue(parsedTemplates.containsKey("TITLE"));
        assertTrue(parsedTemplates.containsKey("SIZE"));
        assertTrue(parsedTemplates.containsKey("DESCRIPTION"));
        assertTrue(parsedTemplates.containsKey("WEIGHT"));

        assertEquals(TitleField.class, parsedTemplates.get("TITLE"));
        assertEquals(SizeField.class, parsedTemplates.get("SIZE"));
        assertEquals(DescriptionField.class, parsedTemplates.get("DESCRIPTION"));
        assertEquals(WeightField.class, parsedTemplates.get("WEIGHT"));
    }

}