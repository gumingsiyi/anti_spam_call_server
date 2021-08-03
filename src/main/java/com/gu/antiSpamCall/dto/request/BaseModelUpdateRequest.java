package com.gu.antiSpamCall.dto.request;

import com.gu.antiSpamCall.model.SpamCallModelConfig;
import lombok.Data;

@Data
public class BaseModelUpdateRequest {
    SpamCallModelConfig config;
}
