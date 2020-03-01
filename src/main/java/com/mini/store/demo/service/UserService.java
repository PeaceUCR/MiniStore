package com.mini.store.demo.service;

import com.mini.store.demo.dto.SignInRequest;
import com.mini.store.demo.dto.SignUpRequest;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.User;

public interface UserService {

    void signUp(SignUpRequest signUpRequest) throws Exception;

    String signIn(SignInRequest signInRequest) throws Exception;

    User getUserById(Integer id) throws BusinessException;

    User getUserByName(String name);
}
