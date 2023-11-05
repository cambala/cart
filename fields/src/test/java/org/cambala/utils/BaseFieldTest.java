package org.cambala.utils;

import org.cambala.fields.implementation.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseFieldTest {

    @Test
    void shouldRemoveUnnecessarySpacesFromName() {
        String name1 = "   Name   ";
        assertEquals("Name", new TextField(name1, "some text").getName());

        String name2 = "   Custom name   ";
        assertEquals("Custom name", new TextField(name2, "some text").getName());
    }
}
