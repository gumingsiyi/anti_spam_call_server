package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.service.SimService;
import com.gu.antiSpamCall.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/getBlackList")
    Result<List<String>> getBlackList(@RequestParam String num) {
        List<String> res = simService.getBlackList(num);
        return Result.success(res);
    }

    @GetMapping("/getWhiteList")
    Result<List<String>> getWhiteList(@RequestParam String num) {
        List<String> res = simService.getWhiteList(num);
        return Result.success(res);
    }
}
