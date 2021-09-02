package com.gu.antiSpamCall.util.evaluator.strategy;

import com.gu.antiSpamCall.model.SpamCallModelConfig;

public class BaseModelEvaluator extends SuperEvaluator<Boolean> {

    private final SpamCallModelConfig config;
    private final long callFromSame;//当天外呼

    public BaseModelEvaluator(SpamCallModelConfig config,
                              long callFromSame
    ) {
        this.config = config;
        this.callFromSame = callFromSame;
    }

    @Override
    public Boolean evaluate() {
        return config.getCallFromSameNumToday() > callFromSame;
    }
}
