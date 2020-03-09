package com.mini.store.demo.controller;

import com.mini.store.demo.dto.CreateOrderRequest;
import com.mini.store.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends BaseController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/order")
    public void create(@RequestBody CreateOrderRequest createOrderRequest) throws Exception{
        orderService.createOrder(createOrderRequest);
    }
}
