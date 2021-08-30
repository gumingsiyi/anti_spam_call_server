package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.annotation.PassToken;
import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.CallInfoResponse;
import com.gu.antiSpamCall.dto.response.LoginResponse;
import com.gu.antiSpamCall.service.AdminService;
import com.gu.antiSpamCall.util.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @PassToken
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    Result<LoginResponse> adminLogin(@RequestBody LoginRequest request) {
        LoginResponse response = adminService.adminLogin(request);
        return new Result<>(response);
    }

    @GetMapping("/getBlackList")
    Result<List<String>> getBlackList(@RequestParam String num) {
        List<String> res = adminService.getBlackList(num);
        return Result.success(res);
    }

    @GetMapping("/getWhiteList")
    Result<List<String>> getWhiteList(@RequestParam String num) {
        List<String> res = adminService.getWhiteList(num);
        return Result.success(res);
    }

    @GetMapping("/getCallInfo")
    public Result<CallInfoResponse> getCallInfo(@RequestParam String from, @RequestParam String to) {
        return Result.success(adminService.queryCallInfo(from, to));
    }
}
