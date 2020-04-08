package com.mini.store.demo.controller;

import com.mini.store.demo.dto.CreateItemRequest;
import com.mini.store.demo.model.Item;
import com.mini.store.demo.security.JwtIgnore;
import com.mini.store.demo.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController extends BaseController {
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping("/item")
    public void create(@RequestBody CreateItemRequest createItemRequest) throws Exception{
        itemService.createItem(createItemRequest);
    }

    @JwtIgnore
    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable Integer id){
        return itemService.getItemById(id);
    }

//    @GetMapping("/items")
//    public List<Item> listItem(){
//        return itemService.listItem();
//    }
    @JwtIgnore
    @GetMapping("/items")
    public List<Item> listItem(@RequestParam(name = "category", required = false) String category, @RequestParam(name = "itemName", required = false) String itemName, @RequestParam(name = "limit", required = false) Integer limit){
        if(category == null && itemName == null) {
            return itemService.listItem(limit);
        }

        if(itemName != null) {
            return itemService.listItemByName(itemName);
        }

        return itemService.listItemByCategory(category);
    }

    @JwtIgnore
    @GetMapping("/ids")
    public List<Item> listItemByIds(){
        Integer[] ids = {1, 2, 3};
        return itemService.listItemByIds(Arrays.asList(ids));
    }
}
