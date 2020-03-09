package com.mini.store.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.mini.store.demo.dao.UserMapper;
import com.mini.store.demo.dto.SignInRequest;
import com.mini.store.demo.dto.SignUpRequest;
import com.mini.store.demo.error.BusinessError;
import com.mini.store.demo.error.BusinessException;
import com.mini.store.demo.model.User;
import com.mini.store.demo.security.Audience;
import com.mini.store.demo.security.JwtTokenUtil;
import com.mini.store.demo.service.UserService;
import com.mini.store.demo.validator.ValidationResult;
import com.mini.store.demo.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Audience audience;
    @Autowired
    private ValidatorImpl validator;

    @Override
    public String signIn(SignInRequest signInRequest) throws Exception {
        String name = signInRequest.getUserName();
        String password = signInRequest.getPassword();
        User user = getUserByName(name);
        if(user == null ){
            throw new BusinessException(BusinessError.USER_NOT_EXIST);
        }
        if(!passwordEncoder.matches(password, user.getEncrptPassword())){
            throw new BusinessException(BusinessError.USER_PASSWORD_ERROR);
        }
        return JwtTokenUtil.createJWT(Integer.toString(user.getUserId()), user.getUserName(), audience);
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) throws Exception{
        ValidationResult validationResult = validator.validate(signUpRequest);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(BusinessError.PARAMETER_ERROR, validationResult.getFormattedMsg());
        }
        String name = signUpRequest.getUserName();
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
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
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

    // get Userid in Request Attr that set by jwt interceptor
    public String getContextUserId() throws Exception {
        ServletRequestAttributes servletRequestAttributes = ofNullable((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).orElseThrow(() -> new BusinessException(BusinessError.NOT_A_REQUEST));
        return ofNullable(
                (String) servletRequestAttributes.getRequest().getAttribute(JwtTokenUtil.ATTR_NAME))
                .orElseThrow(() -> new BusinessException(BusinessError.NO_UID_IN_REQUEST_ATTR));
    }
}
