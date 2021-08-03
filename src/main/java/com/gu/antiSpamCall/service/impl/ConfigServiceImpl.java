package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.ConfigDao;
import com.gu.antiSpamCall.dto.response.SpamCallModelResponse;
import com.gu.antiSpamCall.model.SpamCallModelConfig;
import com.gu.antiSpamCall.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource
    ConfigDao configDao;

    @Override
    public SpamCallModelResponse getConfig(String name) {
        SpamCallModelResponse response = new SpamCallModelResponse();
        SpamCallModelConfig config = configDao.findConfigByName(name);
        response.setConfig(config);
        log.info(String.format("获取防骚扰模型 [%s]", name));
        return response;
    }

    @Override
    public boolean updateBaseConfig(SpamCallModelConfig config) {
        try {
            configDao.updateBaseConfig(config);
            log.info("模型更新成功 [base_config]");
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
