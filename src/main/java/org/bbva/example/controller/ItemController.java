package org.bbva.example.controller;

import org.bbva.example.ItemRepository;
import org.bbva.example.errors.ItemNotFoundException;
import org.bbva.example.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    List<ItemModel> all() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    ItemModel getById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PostMapping("/items")
    ItemModel createNew(@RequestBody ItemModel newItemModel) {
        return itemRepository.save(newItemModel);
    }

    @DeleteMapping("/items/{id}")
    void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/items/{id}")
    ItemModel updateOrCreate(@RequestBody ItemModel newItemModel, @PathVariable Long id) {
        return itemRepository.findById(id)
                .map(itemModel -> {
                    itemModel.setName(newItemModel.getName());

                    return itemRepository.save(itemModel);
                })
                .orElseGet(() -> {
                    newItemModel.setId(id);;

                    return itemRepository.save(newItemModel);
                });
    }

}
