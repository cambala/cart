package org.cambala.cart.models.item;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@ToString
@Data
@Document(collection = "catalog_items")
public class Item {

    @Id
    private UUID id;

    private List<Object> attributes;
}
