package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.model.SpamCallModelConfig;

public interface ConfigService {
    SpamCallModelConfig getConfig(String name);
    boolean updateConfig(SpamCallModelConfig config);
}
