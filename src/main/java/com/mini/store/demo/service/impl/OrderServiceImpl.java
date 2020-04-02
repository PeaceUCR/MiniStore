package com.mini.store.demo.service.impl;

import com.mini.store.demo.dao.DeliveryMapper;
import com.mini.store.demo.dao.OrderItemAssociationMapper;
import com.mini.store.demo.dao.OrderMapper;
import com.mini.store.demo.dto.CreateOrderRequest;
import com.mini.store.demo.dto.ItemInOrder;
import com.mini.store.demo.dto.OrderWithItem;
import com.mini.store.demo.model.Delivery;
import com.mini.store.demo.model.Item;
import com.mini.store.demo.model.Order;
import com.mini.store.demo.model.OrderItemAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemAssociationMapper orderItemAssociationMapper;
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ItemServiceImpl itemService;
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) throws Exception{
        Order order = convertFromCreateItemRequest(createOrderRequest);
        List<ItemInOrder> itemInOrderList = createOrderRequest.getItemList();
        order.setUserId(Integer.parseInt(userService.getContextUserId()));
        order.setDiscount(new Long(0));
        orderMapper.insert(order);
        Integer orderId = order.getOrderId();

        Delivery delivery = createOrderRequest.getDelivery();
        delivery.setOrderId(orderId);
        deliveryMapper.insertSelective(delivery);

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

    public List<OrderWithItem> getAllOrdersOfUser() throws Exception {
        Integer userId = Integer.parseInt(userService.getContextUserId());
        List<Order> orders = orderMapper.listOrdersByUserId(userId);
        if(orders.size() == 0) {
            return new ArrayList<OrderWithItem>();
        }

        List<Integer> itemIds = orders.stream()
                            .flatMap(order -> order.getOrderItemAssociationList().stream())
                            .map(orderItemAssociation -> orderItemAssociation.getItemId()).collect(Collectors.toList());
        List<Item> items = itemService.listItemByIds(itemIds);
        // fill back
        List<OrderWithItem> orderWithItems = orders.stream().map(order -> {
            List<Item> orderItems = order.getOrderItemAssociationList().stream()
                    // map orderItemAssociation to items by itemId
                    .map(orderItemAssociation -> items.stream().filter(item -> item.getItemId().equals(orderItemAssociation.getItemId())).findFirst().get())
                    .collect(Collectors.toList());
            return OrderWithItem.builder().order(order).items(orderItems).build();
        }).collect(Collectors.toList());
        System.out.println(orderWithItems);
        return orderWithItems;

    }

    public void deleteOrder(Integer orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }
}
