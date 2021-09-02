package com.gu.antiSpamCall.util.evaluator;

import com.gu.antiSpamCall.util.evaluator.strategy.SuperEvaluator;

public class SpamEvaluatorContext<T> {
    private final SuperEvaluator<T> evaluator;

    public SpamEvaluatorContext(SuperEvaluator<T> evaluator) {
        this.evaluator = evaluator;
    }

    public T getResult() {
        return evaluator.evaluate();
    }
}
