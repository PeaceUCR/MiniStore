package com.mini.store.demo.controller;

import com.mini.store.demo.dto.SignInRequest;
import com.mini.store.demo.dto.SignUpRequest;
import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.User;
import com.mini.store.demo.response.CommonReturnType;
import com.mini.store.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController extends BaseController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) throws Exception{
        userServiceImpl.signUp(signUpRequest);
    }

    @PostMapping("/signin")
    public void signIn(@RequestBody SignInRequest signInRequest) throws Exception{
        userServiceImpl.signIn(signInRequest);
    }

//    @GetMapping("/signin")
//    public CommonReturnType signin() throws BusinessException {
//        User user = userServiceImpl.getUserById(2);
//        return CommonReturnType.create(user);
//    }
}
