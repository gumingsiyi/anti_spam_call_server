package com.gu.antiSpamCall.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SpamCallModelConfig")
@Data
public class SpamCallModelConfig {
    String name;

    int outCallCountToday;
    int callFromSameNumToday;
    int aveCallDur7Days;
    double switchRate7Days;
    double answerRate7Days;

    double w_outCallCountToday;
    double w_callFromSameNumToday;
    double w_aveCallDur7Days;
    double w_switchRate7Days;
    double w_answerRate7Days;
}
