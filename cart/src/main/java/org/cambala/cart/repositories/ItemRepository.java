package org.cambala.cart.repositories;

import org.cambala.cart.models.item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ItemRepository extends MongoRepository<Item, UUID> {
}
