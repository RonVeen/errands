package org.ninjaware.errands.errands;

import org.ninjaware.errands.errands.controller.DataNotFoundException;
import org.ninjaware.errands.errands.domain.Group;
import org.ninjaware.errands.errands.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    public static final String GROUP = "Group";

    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public List<Group> findAll() {
        return repository.findAll();
    }

    public Group findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(GROUP, id));
    }

    public Group save(Group group) {
        return repository.save(group);
    }

    public Group update(String id, Group updatedGroup) {
        var group = findById(id);
        group.setName(updatedGroup.getName());
        return repository.save(group);
    }

    public Group remove(String id) {
        var group = findById(id);
        repository.delete(group);
        return group;
    }

}
