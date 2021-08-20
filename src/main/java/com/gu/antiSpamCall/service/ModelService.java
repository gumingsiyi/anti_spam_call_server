package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.dto.response.SpamCallModelResponse;
import com.gu.antiSpamCall.model.SpamCallModelConfig;

public interface ModelService {
    SpamCallModelResponse getConfig(String name);
    boolean updateBaseConfig(SpamCallModelConfig config);
    long queryCallCountToday(String from, String to);
}
