package com.mini.store.demo.service.impl;

import com.mini.store.demo.dao.ItemMapper;
import com.mini.store.demo.dto.CreateItemRequest;
import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.Item;
import com.mini.store.demo.model.ItemStock;
import com.mini.store.demo.validator.ValidationResult;
import com.mini.store.demo.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl {
    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemStockServiceImpl itemStockService;

    @Autowired
    private UserServiceImpl userService;

    @Transactional
    public void createItem(CreateItemRequest createItemRequest) throws Exception{
        ValidationResult validationResult = validator.validate(createItemRequest);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(BusinessError.PARAMETER_ERROR, validationResult.getFormattedMsg());
        }
        Item item = convertFromCreateItemRequest(createItemRequest);
        item.setUserId(Integer.parseInt(userService.getContextUserId()));
        itemMapper.insertSelective(item);
        int itemId = item.getItemId();
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStock(100);
        itemStock.setItemId(itemId);
        itemStock.setCreateDate(new Date());
        itemStock.setUpdateDate(new Date());
        itemStockService.createItemStock(itemStock);
    }

    private Item convertFromCreateItemRequest(CreateItemRequest createItemRequest){
        if(createItemRequest == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(createItemRequest, item);
        item.setCreateDate(new Date());
        item.setUpdateDate(new Date());
        return item;
    }

    public Item getItemById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    public List<Item> listItem() {
        return itemMapper.listItem();
    }
}
