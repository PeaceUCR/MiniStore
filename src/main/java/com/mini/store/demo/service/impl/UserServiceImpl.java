package com.mini.store.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.mini.store.demo.dao.UserMapper;
import com.mini.store.demo.dto.SignInRequest;
import com.mini.store.demo.dto.SignUpRequest;
import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.User;
import com.mini.store.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signIn(SignInRequest signInRequest) throws Exception {
        String name = signInRequest.getName();
        String password = signInRequest.getPassword();
        User user = getUserByName(name);
        if(user == null ){
            throw new BusinessException(BusinessError.USER_NOT_EXIST);
        }


        if(!passwordEncoder.matches(password, user.getEncrptPassword())){
            throw new BusinessException(BusinessError.USER_PASSWORD_ERROR);
        }
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) throws Exception{
        String name = signUpRequest.getName();
        User user = getUserByName(name);
        if(user != null ){
            throw new BusinessException(BusinessError.USER_ALREADY_EXIST);
        }
        User newUser = convertFromSignUpRequest(signUpRequest);
        newUser.setEncrptPassword(encodePassword(signUpRequest.getPassword()));
        //insertSelective only insert property not null, https://coding.imooc.com/lesson/338.html#mid=26213
        userMapper.insertSelective(newUser);
    }

    private User convertFromSignUpRequest(SignUpRequest signUpRequest) {
        if(signUpRequest == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(signUpRequest, user);
        return user;
    }
    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
    @Override
    public User getUserById(Integer id) throws BusinessException {
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null) {
            throw new BusinessException(BusinessError.USER_NOT_EXIST);
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.selectByName(name);
    }
}
