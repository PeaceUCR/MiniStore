package com.mini.store.demo.service.impl;

import com.mini.store.demo.dao.ItemMapper;
import com.mini.store.demo.dao.ItemStockMapper;
import com.mini.store.demo.dto.CreateItemRequest;
import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.Item;
import com.mini.store.demo.model.ItemStock;
import com.mini.store.demo.security.JwtTokenUtil;
import com.mini.store.demo.validator.ValidationResult;
import com.mini.store.demo.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Optional.ofNullable;

@Service
public class ItemServiceImpl {
    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemStockServiceImpl itemStockService;

    @Transactional
    public void createItem(CreateItemRequest createItemRequest) throws Exception{
        ValidationResult validationResult = validator.validate(createItemRequest);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(BusinessError.PARAMETER_ERROR, validationResult.getFormattedMsg());
        }
        Item item = convertFromCreateItemRequest(createItemRequest);
        item.setUserId(Integer.parseInt(getContextUserId()));
        itemMapper.insertSelective(item);
        int itemId = item.getId();
        ItemStock itemStock = new ItemStock();
        itemStock.setStock(100);
        itemStock.setItemId(itemId);
        itemStockService.createItemStock(itemStock);
    }

    private Item convertFromCreateItemRequest(CreateItemRequest createItemRequest){
        if(createItemRequest == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(createItemRequest, item);
        return item;
    }
    // get Userid in Request Attr that set by jwt interceptor
    private String getContextUserId() throws Exception {
        ServletRequestAttributes servletRequestAttributes = ofNullable((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).orElseThrow(() -> new BusinessException(BusinessError.NOT_A_REQUEST));
        return ofNullable(
                (String) servletRequestAttributes.getRequest().getAttribute(JwtTokenUtil.ATTR_NAME))
                .orElseThrow(() -> new BusinessException(BusinessError.NO_UID_IN_REQUEST_ATTR));
    }

    public Item getItemById() {
        return null;
    }
}
