package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.dto.request.BaseModelUpdateRequest;
import com.gu.antiSpamCall.dto.response.ModelUpdateResponse;
import com.gu.antiSpamCall.dto.response.SpamCallModelResponse;
import com.gu.antiSpamCall.service.ConfigService;
import com.gu.antiSpamCall.util.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(value = "防骚扰模型controller")
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Resource
    ConfigService configService;

    @GetMapping("/getBaseModel")
    public Result<SpamCallModelResponse> querySpamBaseCallModel() {
        SpamCallModelResponse response = configService.getConfig("base_config");
        return Result.success(response);
    }

    @PostMapping("/setBaseModel")
    public Result<ModelUpdateResponse> updateSpamBaseModel(@RequestBody BaseModelUpdateRequest request) {
        ModelUpdateResponse response = new ModelUpdateResponse();
        boolean result = configService.updateBaseConfig(request.getConfig());
        response.setResult(result);
        return Result.success(response);
    }
}
