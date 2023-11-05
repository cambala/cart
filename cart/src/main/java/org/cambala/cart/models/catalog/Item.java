package org.cambala.cart.models.catalog;

import lombok.Builder;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@ToString
@Document(collection = "catalog_items")
public class Item {

    @Id
    UUID id;

    List<Object> attributes;
}
