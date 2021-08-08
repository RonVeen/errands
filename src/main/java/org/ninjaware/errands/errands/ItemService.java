package org.ninjaware.errands.errands;

import org.ninjaware.errands.errands.controller.DataNotFoundException;
import org.ninjaware.errands.errands.domain.Item;
import org.ninjaware.errands.errands.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    public static final String ITEM = "Item";

    private final ItemRepository repository;
    private final GroupService groupService;

    public ItemService(ItemRepository repository, GroupService groupService) {
        this.repository = repository;
        this.groupService = groupService;
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(ITEM, id));
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public Item update(String id, Item updatedItem) {
        var item = findById(id);
        item.setName(updatedItem.getName());
        return repository.save(item);
    }

    public Item removeItem(String id) {
        var item = findById(id);
        repository.delete(item);
        return item;
    }

    public Item updateGroup(String id, String groupId) {
        var item = findById(id);
        var group = groupService.findById(groupId);
        item.setGroup(group);
        return repository.save(item);
    }

}
