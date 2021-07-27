package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.model.SpamCallModelConfig;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
}
