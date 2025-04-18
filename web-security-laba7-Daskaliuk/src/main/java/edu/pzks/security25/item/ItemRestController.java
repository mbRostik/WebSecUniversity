package edu.pzks.security25.item;
/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  17.03.2025 -19.40
        */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/items")
@AllArgsConstructor

public class ItemRestController {
     private final ItemService service;

        @GetMapping
     public List<Item> getItems(){
         return service.getAll();
     }
        @GetMapping("/{id}")
     public Item getOneItem(@PathVariable String id){
         return service.getById(id);
     }
        @DeleteMapping("/{id}")
     public void delete(@PathVariable String id){
          service.deleteById(id);
     }

    @PostMapping
    public Item create(@RequestBody Item item){
            return  service.create(item);
    }

    @PutMapping
    public Item update(@RequestBody Item item){
        return  service.update(item);
    }



    @GetMapping("/hello/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {

            return "Hello User!";
    }


    @GetMapping("/hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }


    @GetMapping("/hello/stranger")
    public String helloStranger() {
        return "Hello Stranger!";
    }







}
