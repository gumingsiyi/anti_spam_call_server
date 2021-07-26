package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.LoginResponse;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.service.LoginService;
import com.gu.antiSpamCall.service.UserService;
import com.gu.antiSpamCall.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserService userService;

    @Override
    public LoginResponse adminLogin(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        AdminUser res = userService.queryUserByNameAndPwd(request.getUsername(), request.getPassword());
        if (res == null) {
            response.setResult(false);
            response.setMessage("用户名密码错误，登录失败。");
        } else {
            log.info(String.format("用户 [%s] 登录成功.", res.getUsername()));
            String token = TokenUtil.createSignByJWT(res);
            response.setResult(true);
            response.setUsername(res.getUsername());
            response.setNickname(res.getNickname());
            response.setToken(token);
            response.setMessage("登录成功。");
        }
        return response;
    }
}
