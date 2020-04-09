package com.mini.store.demo.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.mini.store.demo.dto.WechatLogInRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WxServiceImpl {

    private final WxMaService wxMaService;
    // for wx.getUserInfo
    // at front end, the wx.login should called first, then getUserDetails
    public WxMaUserInfo getWxUserInfo(String sessionKey, WechatLogInRequest wechatLogInRequest) throws Exception {

        if (!wxMaService.getUserService().checkUserInfo(sessionKey, wechatLogInRequest.getRawData(), wechatLogInRequest.getSignature())) {
            log.error("Check User Info Failed: sessionKey: {}, rawData: {}, signature: {}", sessionKey, wechatLogInRequest.getRawData(), wechatLogInRequest.getSignature());
            log.error("Check User Info Failed: encryptedData: {}, iv: {}", wechatLogInRequest.getEncryptedData(), wechatLogInRequest.getIv());
            throw new Exception("验证错误");
        }
        return wxMaService.getUserService().getUserInfo(sessionKey, wechatLogInRequest.getEncryptedData(), wechatLogInRequest.getIv());
    }
    // for wx.login
    // request is sent by http client https://github.com/Wechat-Group/WxJava/blob/23c1b0b0df621cf29ed57a92ae5fa287ff749f0f/weixin-java-common/src/main/java/me/chanjar/weixin/common/util/http/SimpleGetRequestExecutor.java
    // but what I use is restTemplate
    // https://www.baeldung.com/spring-webclient-resttemplate
    public WxMaJscode2SessionResult getSessionInfo(String code) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
        if (log.isDebugEnabled()) {
            log.debug("User login Successful! openId: {}, sessionKey: {}", session.getOpenid(), session.getSessionKey());
        }
        return session;
    }
}
