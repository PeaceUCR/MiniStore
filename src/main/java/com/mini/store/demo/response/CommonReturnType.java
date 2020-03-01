package com.mini.store.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CommonReturnType {
    private Integer status;

    private Object data;

    public static CommonReturnType create(Object data) {
        return CommonReturnType.builder().status(HttpStatus.OK.value()).data(data).build();
    }
}
