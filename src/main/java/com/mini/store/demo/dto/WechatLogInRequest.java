package com.mini.store.demo.dto;

import lombok.Data;

@Data
public class WechatLogInRequest {
  private String code;
  private String encryptedData;
  private String iv;
  private String rawData;
  private String signature;
}
