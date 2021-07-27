package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.ConfigDao;
import com.gu.antiSpamCall.model.SpamCallModelConfig;
import com.gu.antiSpamCall.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource
    ConfigDao configDao;

    @Override
    public SpamCallModelConfig getConfig(String name) {
        return null;
    }

    @Override
    public boolean updateConfig(SpamCallModelConfig config) {
        return false;
    }
}
