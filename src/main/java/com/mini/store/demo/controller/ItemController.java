package com.mini.store.demo.controller;

import com.mini.store.demo.dto.CreateItemRequest;
import com.mini.store.demo.model.Item;
import com.mini.store.demo.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController extends BaseController {
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping("/item")
    public void create(@RequestBody CreateItemRequest createItemRequest) throws Exception{
        itemService.createItem(createItemRequest);
    }

    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable Integer id){
        return itemService.getItemById(id);
    }

    @GetMapping("/items")
    public List<Item> listItem(){
        return itemService.listItem();
    }

}
