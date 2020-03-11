package com.mini.store.demo.service.impl;

import com.mini.store.demo.dao.OrderItemAssociationMapper;
import com.mini.store.demo.dao.OrderMapper;
import com.mini.store.demo.dto.CreateOrderRequest;
import com.mini.store.demo.dto.ItemInOrder;
import com.mini.store.demo.model.Order;
import com.mini.store.demo.model.OrderItemAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemAssociationMapper orderItemAssociationMapper;

    @Autowired
    private UserServiceImpl userService;
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) throws Exception{
        Order order = convertFromCreateItemRequest(createOrderRequest);
        List<ItemInOrder> itemInOrderList = createOrderRequest.getItemList();
        order.setUserId(Integer.parseInt(userService.getContextUserId()));
        order.setDiscount(new Long(0));
        orderMapper.insert(order);
        Integer orderId = order.getOrderId();

        List<OrderItemAssociation> orderItemAssociationList = new ArrayList<OrderItemAssociation>();
        itemInOrderList.forEach(itemInOrder -> {
            OrderItemAssociation orderItemAssociation = new OrderItemAssociation();
            orderItemAssociation.setOrderId(orderId);
            orderItemAssociation.setItemId(itemInOrder.getItemId());
            orderItemAssociation.setQuantity(itemInOrder.getQuantity());
            orderItemAssociation.setCreateDate(new Date());
            orderItemAssociation.setUpdateDate(new Date());
            orderItemAssociationList.add(orderItemAssociation);
        });
        orderItemAssociationMapper.insertBatch(orderItemAssociationList);
    }

    private Order convertFromCreateItemRequest(CreateOrderRequest createOrderRequest){
        if(createOrderRequest == null) {
            return null;
        }
        Order order = new Order();
        BeanUtils.copyProperties(createOrderRequest, order);
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        return order;
    }

    public Order getOrderById(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
