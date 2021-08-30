package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.annotation.PassToken;
import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.LoginResponse;
import com.gu.antiSpamCall.service.LoginService;
import com.gu.antiSpamCall.util.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AdminController {
    @Resource
    LoginService loginService;

    @PassToken
    @ApiOperation(value = "登录")
    @PostMapping("/admin/login")
    Result<LoginResponse> adminLogin(@RequestBody LoginRequest request) {
        LoginResponse response = loginService.adminLogin(request);
        return new Result<>(response);
    }
}
