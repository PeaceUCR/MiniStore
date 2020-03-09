package com.mini.store.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class ItemInOrder {
    @NotNull(message = "商品id不能为空")
    private Integer itemId;
    @NotNull(message = "商品数量不能为空")
    private Integer quantity;
}
