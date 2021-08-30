package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.dto.response.CallInfoResponse;
import com.gu.antiSpamCall.model.CallRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 通话记录的DAO
 */
@Component
public class CallDao {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 通话记录如果没有记录则新生成一条，否则在旧记录上+1
     * @param record 通话记录
     */
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

    /**
     * 记录拦截次数
     * @param record 通话记录
     */
    public void anti(CallRecord record) {
        Query query = new Query(Criteria
                .where("from").is(record.getFrom())
                .and("to").is(record.getTo())
                .and("time").is(record.getTime())
        );
        CallRecord res = mongoTemplate.findOne(query, CallRecord.class);
        if (res != null) {
            Update update = new Update();
            update.set("anti", res.getAnti()+1);
            mongoTemplate.updateFirst(query, update, CallRecord.class);
        } else {
            mongoTemplate.save(record);
        }
    }

    /**
     * 返回当天对应主被叫号码的拨打次数
     * @param record 通话记录
     * @return 当天拨打次数
     */
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

    /**
     * 返回某天对应主被叫号码的拦截次数
     * @param record 主被叫号码以及时间
     * @return 拦截次数
     */
    public long antiCount(CallRecord record) {
        Query query = new Query(Criteria
                .where("from").is(record.getFrom())
                .and("to").is(record.getTo())
                .and("time").is(record.getTime())
        );
        CallRecord res = mongoTemplate.findOne(query, CallRecord.class);
        if (res != null) {
            return res.getAnti();
        } else {
            return 0;
        }
    }

    public CallInfoResponse getCallInfo(CallRecord record) {
        Query query = new Query(Criteria
                .where("from").is(record.getFrom())
                .and("to").is(record.getTo())
                .and("time").is(record.getTime())
        );
        CallRecord callInfo = mongoTemplate.findOne(query, CallRecord.class);
        CallInfoResponse response = new CallInfoResponse();
        if (callInfo != null) {
            response.setCallCount(callInfo.getCount());
            response.setAntiCount(callInfo.getAnti());
        } else {
            response.setCallCount(0);
            response.setAntiCount(0);
        }
        return response;
    }

    public void clear() {
        Query query = new Query(new Criteria());
        mongoTemplate.findAllAndRemove(query, CallRecord.class);
    }
}
