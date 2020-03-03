package com.mini.store.demo.controller;

import com.mini.store.demo.model.ItemStock;
import com.mini.store.demo.service.impl.ItemStockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemStockController {
    @Autowired
    private ItemStockServiceImpl itemStockService;

    @GetMapping("/item-stock")
    public ItemStock create(@RequestParam Integer itemId){
        return itemStockService.getItemStockByItemId(itemId);
    }
}
