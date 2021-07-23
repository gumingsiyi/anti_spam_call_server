package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.LoginResponse;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.service.LoginService;
import com.gu.antiSpamCall.service.UserService;
import com.gu.antiSpamCall.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
