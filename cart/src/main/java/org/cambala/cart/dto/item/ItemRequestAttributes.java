package org.cambala.cart.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ToString
@Getter
public class ItemRequestAttributes {

    @JsonProperty("templateFields")
    List<Map<String, String>> templateFields = new ArrayList<>();

    @JsonProperty("customFields")
    List<Map<String, String>> customFields = new ArrayList<>();
}
