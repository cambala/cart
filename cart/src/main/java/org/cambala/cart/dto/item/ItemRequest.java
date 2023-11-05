package org.cambala.cart.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@ToString
@Getter
public class ItemRequest {

    @JsonProperty("id")
    UUID id;
    @JsonProperty("attributes")
    List<Map<String, String>> attributes = new ArrayList<>();
}

