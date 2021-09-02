package com.gu.antiSpamCall.util.evaluator.strategy;

import com.gu.antiSpamCall.model.SpamCallModelConfig;

public class BaseModel2Evaluator extends SuperEvaluator<Double> {
    private final SpamCallModelConfig config;
    private final long callCount;//当天外呼
    private final long callFromSame;//拨打同一被叫重复次数
    private final long aveCallDur;//平均通话时长
    private final double aveSwitchRate;//平均接通率
    private final double aveAnsRate;//平均接通率

    public BaseModel2Evaluator(SpamCallModelConfig config,
                              long callCount,
                              long callFromSame,
                              long aveCallDur,
                              double aveSwitchRate,
                              double aveAnsRate
    ) {
        this.config = config;
        this.callCount = callCount;
        this.callFromSame = callFromSame;
        this.aveCallDur = aveCallDur;
        this.aveSwitchRate = aveSwitchRate;
        this.aveAnsRate = aveAnsRate;
    }



    @Override
    public Double evaluate() {
        double a1, a2, a3, a4, a5;
        if (callCount > config.getOutCallCountToday()) {
            a1 = 0;
        } else {

        }
        return 0.0;
    }

}
