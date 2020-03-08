package com.mini.store.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateItemRequest {
    @NotBlank(message = "商品名不能为空")
    private String itemName;
    @NotNull(message = "单位价格不能为空")
    private Long itemPrice;
    @NotBlank(message = "单位不能为空")
    private String itemPriceUnit;
    @NotBlank(message = "描述不能为空")
    private String itemDescription;
    private String imgUrl;
}
