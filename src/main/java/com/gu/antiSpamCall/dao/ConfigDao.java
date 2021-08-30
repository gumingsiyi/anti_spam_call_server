package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.model.SpamCallModelConfig;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 判断模型的DAO
 */
@Component
public class ConfigDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public SpamCallModelConfig findConfigByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(
                query,
                SpamCallModelConfig.class);
    }

    public void updateBaseConfig(SpamCallModelConfig config) {
        Query query = new Query(Criteria.where("name").is("base_config"));
        Update update = new Update();
        update.set("outCallCountToday", config.getOutCallCountToday());
        update.set("w_outCallCountToday", config.getW_outCallCountToday());
        update.set("callFromSameNumToday", config.getCallFromSameNumToday());
        update.set("w_callFromSameNumToday", config.getW_callFromSameNumToday());
        update.set("aveCallDur7Days", config.getAveCallDur7Days());
        update.set("switchRate7Days", config.getW_switchRate7Days());
        update.set("w_switchRate7Days", config.getW_switchRate7Days());
        update.set("answerRate7Days", config.getAnswerRate7Days());
        update.set("w_answerRate7Days", config.getW_answerRate7Days());
        mongoTemplate.updateFirst(query, update, SpamCallModelConfig.class);
    }
}
