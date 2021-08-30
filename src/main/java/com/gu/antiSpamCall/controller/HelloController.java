package com.gu.antiSpamCall.controller;

import com.alibaba.fastjson.JSON;
import com.gu.antiSpamCall.dao.AdminUserDao;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "测试controller")
@RestController
@RequestMapping("/test")
public class HelloController {

    @Resource
    private AdminUserDao adminUserDao;

    @ApiOperation(value = "测试hello world")
    @GetMapping("/hello")
    public String hello() {
        log.info("Visit hello page.");
        return "Hello, Spring Boot!";
    }

    @ApiOperation(value = "测试用户名查询")
    @PostMapping("/findOneTest")
    public AdminUser findOne(String name) {
        AdminUser res = adminUserDao.findUserByName(name);
        System.out.println("test " + res);

        return res;
    }

    @ApiOperation(value = "token测试")
    @GetMapping("/tokenTest")
    public String tokenTest() {
        Result<Object> res = Result.success("token有效");
        return JSON.toJSONString(res);
    }

    @ApiOperation(value = "测试带参数的get请求")
    @GetMapping("/getTest")
    public String getTest(@RequestParam String s) {
        Result<Object> res = Result.success(s);
        return JSON.toJSONString(res);
    }
}
