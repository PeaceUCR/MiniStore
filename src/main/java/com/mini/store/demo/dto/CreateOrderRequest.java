package com.mini.store.demo.dto;

import com.mini.store.demo.model.Delivery;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull(message = "总价不能为空")
    private Long totalAmount;
    @NotEmpty(message = "订单商品列表不能为空")
    private List<ItemInOrder> itemList;

    private Delivery delivery;
}
