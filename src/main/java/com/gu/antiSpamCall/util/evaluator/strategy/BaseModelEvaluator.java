package com.gu.antiSpamCall.util.evaluator.strategy;

import com.gu.antiSpamCall.model.SpamCallModelConfig;
import com.gu.antiSpamCall.util.evaluator.strategy.SuperEvaluator;

public class BaseModelEvaluator extends SuperEvaluator {

    private final SpamCallModelConfig config;
    private final long callCountToday;

    public BaseModelEvaluator(SpamCallModelConfig config, long callCountToday) {
        this.config = config;
        this.callCountToday = callCountToday;
    }

    @Override
    public boolean evaluate() {
        return config.getCallFromSameNumToday() >= callCountToday;
    }
}
