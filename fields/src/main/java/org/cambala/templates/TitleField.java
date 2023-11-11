package org.cambala.templates;

import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.fields.implementation.TextField;

@NoArgsConstructor
@Template(templateId = "TITLE", templateName = "Наименование")
public class TitleField extends AbstractTemplate {

    private TextField title;

    public TitleField(TextField title) {
        this.title = title;
    }

    public TitleField(String title) {
        this.title = new TextField(this.getTemplateName(), title);
    }

    public TextField getTitle() {
        return title;
    }

    @Override
    public String description() {
        return String.format("%s: %s", title.getName(), title.getValue());
    }
}
