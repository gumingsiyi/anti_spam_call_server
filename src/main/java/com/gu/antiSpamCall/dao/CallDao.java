package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.model.CallRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class CallDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public void save(CallRecord record) {
        Query query = new Query(Criteria
                .where("from").is(record.getFrom())
                .and("to").is(record.getTo())
                .and("time").is(record.getTime())
        );
        CallRecord res = mongoTemplate.findOne(query, CallRecord.class);
        if (res != null) {
            Update update = new Update();
            update.set("count", res.getCount()+1);
            mongoTemplate.updateFirst(query, update, CallRecord.class);
        } else {
            mongoTemplate.save(record);
        }
    }

    public long phoneCallCountByTime(CallRecord record) {
        Query query = new Query(Criteria
                .where("from").is(record.getFrom())
                .and("to").is(record.getTo())
                .and("time").is(record.getTime())
        );
        CallRecord res = mongoTemplate.findOne(query, CallRecord.class);
        if (res != null) {
            return res.getCount();
        } else {
            return 0;
        }
    }

    public void clear() {
        Query query = new Query(new Criteria());
        mongoTemplate.findAllAndRemove(query, CallRecord.class);
    }
}
