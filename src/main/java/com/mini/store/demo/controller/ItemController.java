package com.mini.store.demo.controller;

import com.mini.store.demo.dto.CreateItemRequest;
import com.mini.store.demo.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping("/create")
    public void create(@RequestBody CreateItemRequest createItemRequest) throws Exception{
        itemService.createItem(createItemRequest);
    }
}
