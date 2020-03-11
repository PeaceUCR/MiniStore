package com.mini.store.demo.controller;

import com.mini.store.demo.dto.CreateOrderRequest;
import com.mini.store.demo.model.Order;
import com.mini.store.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends BaseController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/order")
    public void create(@RequestBody CreateOrderRequest createOrderRequest) throws Exception{
        orderService.createOrder(createOrderRequest);
    }

    @GetMapping("/order/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId);
    }
}
