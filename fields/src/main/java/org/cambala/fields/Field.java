package org.cambala.fields;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.cambala.measurement.Measure;
import org.cambala.values.Value;

import java.io.Serializable;

@Setter
public abstract class Field<T> implements Serializable, Described {

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String name;
    @JsonProperty("type")
    protected FieldType type;
    @JsonProperty("measure")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Measure measure;
    @JsonProperty("value")
    protected Value<T> value;
}
