package com.mini.store.demo.dto;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
