package com.gu.antiSpamCall.controller;

import com.gu.antiSpamCall.dto.request.BaseModelUpdateRequest;
import com.gu.antiSpamCall.dto.response.ModelUpdateResponse;
import com.gu.antiSpamCall.dto.response.SpamCallModelResponse;
import com.gu.antiSpamCall.service.ModelService;
import com.gu.antiSpamCall.util.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(value = "防骚扰模型controller")
@RestController
@RequestMapping("/model")
public class ModelController {
    @Resource
    ModelService modelService;

    @GetMapping("/getBaseModel")
    public Result<SpamCallModelResponse> querySpamBaseCallModel() {
        SpamCallModelResponse response = modelService.getConfig("base_config");
        return Result.success(response);
    }

    @PostMapping("/setBaseModel")
    public Result<ModelUpdateResponse> updateSpamBaseModel(@RequestBody BaseModelUpdateRequest request) {
        ModelUpdateResponse response = new ModelUpdateResponse();
        boolean result = modelService.updateBaseConfig(request.getConfig());
        response.setResult(result);
        return Result.success(response);
    }

    @GetMapping("/getCallCountToday")
    public Result<Long> getCallCountToday(String from, String to) {
        return Result.success(modelService.queryCallCountToday(from, to));
    }
}
