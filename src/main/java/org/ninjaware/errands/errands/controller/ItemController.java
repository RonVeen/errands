package org.ninjaware.errands.errands.controller;

import org.ninjaware.errands.errands.ItemService;
import org.ninjaware.errands.errands.domain.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> allItems() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") String id) {
        return new ResponseEntity<>(itemService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item newItem) {
        return new ResponseEntity<>(itemService.save(newItem), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") String id, @RequestBody Item updatedItem) {
        return new ResponseEntity<>(itemService.update(id, updatedItem), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/groups/{groupId}")
    public ResponseEntity<Item> setGroup(@PathVariable("id") String id, @PathVariable("groupId") String groupId) {
        return new ResponseEntity<>(itemService.updateGroup(id, groupId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Item> removeItem(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(itemService.removeItem(id), HttpStatus.OK);
    }


}
