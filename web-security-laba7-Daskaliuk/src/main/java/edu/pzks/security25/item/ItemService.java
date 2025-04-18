package edu.pzks.security25.item;
/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  17.03.2025 -19.40
*/

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ItemService  {
    private final ItemRepository repository;

    private List<Item> items;

    @PostConstruct
    void init(){
        items.add(new Item("1","name1","description1"));
        items.add(new Item("2","name2","description2"));
        items.add(new Item("3","name3","description3"));
        items.add(new Item("4", "Intrusion Detection System", "Monitors network traffic for suspicious activities."));
        items.add(new Item("5", "Access Control List", "Defines permissions for users and systems to access resources."));
        items.add(new Item("6", "Multi-Factor Authentication", "An authentication method requiring multiple verification factors."));
        repository.saveAll(items);
    }


    public List<Item> getAll() {
        return repository.findAll();
    }



    public Item getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById( String id) {
      repository.deleteById(id);
    }

    public Item create(Item item) {
         return repository.save(item);
    }

    public Item update(Item item) {
        return repository.save(item);
    }
}
