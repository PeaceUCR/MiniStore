package com.mini.store.demo.service.impl;

import com.mini.store.demo.dao.ItemStockMapper;
import com.mini.store.demo.model.ItemStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemStockServiceImpl {
    @Autowired
    private ItemStockMapper itemStockMapper;

    public void createItemStock(ItemStock itemStock) {
        itemStockMapper.insertSelective(itemStock);
    }

    public ItemStock getItemStockByItemId(Integer itemId) {
        return itemStockMapper.selectByItemId(itemId);
    }
}
