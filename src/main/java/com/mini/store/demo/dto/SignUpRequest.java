package com.mini.store.demo.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String password;
    private String name;
    private String phone;
}
