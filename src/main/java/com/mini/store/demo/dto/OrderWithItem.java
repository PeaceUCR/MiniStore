package com.mini.store.demo.dto;

import com.mini.store.demo.model.Item;
import com.mini.store.demo.model.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class OrderWithItem {
  private Order order;
  private List<Item> items;
}
