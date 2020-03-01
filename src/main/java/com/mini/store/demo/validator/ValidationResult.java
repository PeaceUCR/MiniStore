package com.mini.store.demo.validator;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Getter
@Setter
public class ValidationResult {
    //校验结果是否有错
    private boolean hasErrors = false;
    //<property, msg>
    private Map<String, String> errorMsgMAP = new HashMap<>();

    //get formatted error msg
    public String getFormattedMsg() {
//        StringBuilder stringBuilder = new StringBuilder();
//        Arrays.stream(errorMsgMAP.values().toArray()).forEach((msg) -> {
//            stringBuilder.append(msg);
//        });
//        return stringBuilder.toString();
        //https://stackoverflow.com/questions/794248/a-method-to-reverse-effect-of-java-string-split
        StringJoiner stringJoiner = new StringJoiner(",");
        Arrays.stream(errorMsgMAP.values().toArray()).forEach((msg) -> {
            stringJoiner.add((String)msg);
        });
        return stringJoiner.toString();
    }
}
