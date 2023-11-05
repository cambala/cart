package org.cambala.templates;

import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.fields.implementation.TextField;

@NoArgsConstructor
@Template(templateId = "DESCRIPTION", templateName = "Описание")
public class DescriptionField extends AbstractTemplate {

    private TextField description;

    public DescriptionField(String description) {
        this.description = new TextField(this.getTemplateName(), description);
    }

    @Override
    public String description() {
        return String.format("%s: %s", this.getTemplateName(), description.getValue());
    }
}
