package com.gu.antiSpamCall.util.evaluator;

import com.gu.antiSpamCall.util.evaluator.strategy.SuperEvaluator;

public class SpamEvaluatorContext {
    private SuperEvaluator evaluator;

    public SpamEvaluatorContext(SuperEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public boolean getResult() {
        return evaluator.evaluate();
    }
}
