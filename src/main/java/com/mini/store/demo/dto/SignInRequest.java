package com.mini.store.demo.dto;
import lombok.Data;

@Data
public class SignInRequest {
    private String password;
    private String userName;
}
