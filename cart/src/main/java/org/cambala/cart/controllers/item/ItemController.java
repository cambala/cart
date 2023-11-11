package org.cambala.cart.controllers.item;

import lombok.RequiredArgsConstructor;
import org.cambala.cart.conversion.item.ItemConverter;
import org.cambala.cart.dto.item.ItemRequest;
import org.cambala.cart.models.item.Item;
import org.cambala.cart.repositories.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository repository;

    @GetMapping
    public List<Item> findAllItems() {
        return repository.findAll();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> findItemById(@PathVariable("itemId") UUID itemId) {
        Item item = repository.findById(itemId).orElse(null);
        return new ResponseEntity<>(item, item == null ? NOT_FOUND : OK);
    }

    @PostMapping
    public void create(@RequestBody ItemRequest request) {
        Item item = ItemConverter.toItem(request);
        repository.save(item);
    }
}
