package com.mini.store.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModal {
    private Integer id;
    private String name;
    private String phone;
    private String wechat_open_id;
}
