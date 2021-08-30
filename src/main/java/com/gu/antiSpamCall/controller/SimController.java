package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.service.SimService;
import com.gu.antiSpamCall.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 模拟拨打等动作的相关功能
 */
@RequestMapping("/simulator")
@RestController
public class SimController {
    @Resource
    SimService simService;

    @GetMapping("/phoneCall")
    Result<String> simPhoneCall() {
        String res = simService.simCall("15700083072", "18996478090");
        return Result.success(res);
    }

    @GetMapping("/reset")
    Result<Boolean> reset() {
        simService.clearCallRecord();
        return Result.success(Boolean.TRUE);
    }
}
