package org.cambala.templates;

import lombok.ToString;
import org.cambala.aop.Template;
import org.cambala.fields.Described;

@ToString
public abstract class AbstractTemplate implements Described {

    private String templateId;
    private String templateName;

    public AbstractTemplate() {
        Template annotation = this.getClass().getAnnotation(org.cambala.aop.Template.class);
        this.templateId = annotation.templateId();
        this.templateName = annotation.templateName();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
