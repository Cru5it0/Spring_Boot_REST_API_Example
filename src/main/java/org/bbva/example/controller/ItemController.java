package org.bbva.example.controller;

import org.bbva.example.ItemRepository;
import org.bbva.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    List<Item> all() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    Item getById(@PathVariable Long id) {
        return itemRepository.findById(id).get();
    }

    @PostMapping("/items")
    Item createNew(@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    @DeleteMapping("/items/{id}")
    void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/items/{id}")
    Item updateOrCreate(@RequestBody Item newItem, @PathVariable Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());

                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);;

                    return itemRepository.save(newItem);
                });
    }

}
