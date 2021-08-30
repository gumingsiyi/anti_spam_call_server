package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.model.BWListModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 黑白名单的DAO
 */
@Component
public class BWListDao {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 通用编辑方法，可用于黑白名单的添加或删除号码
     * @param action "add" or "remove"
     * @param type  "black" or "white"
     * @param from  主叫号码
     * @param to    被叫号码
     */
    public void editList(String action, String type, String from, String to) {
        Query query = new Query(Criteria.where("mobNum").is(to));
        BWListModel bwListModel = mongoTemplate.findOne(query, BWListModel.class);
        if (bwListModel != null) {
            List<String> list;
            if ("black".equals(type)) {
                list = bwListModel.getBlackList();
            } else {
                list = bwListModel.getWhiteList();
            }
            if ("add".equals(action)) {
                list = addUnique(list, from);
            } else {
                list.remove(from);
            }
            Update update = new Update();
            update.set(type + "List", list);
            mongoTemplate.updateFirst(query, update, BWListModel.class);
        } else {
            throw new NullPointerException("该号码未注册黑白名单...");
        }
    }

    /**
     * 返回黑名单或者白名单
     * @param type black or white
     * @param num 该号码的黑白名单
     * @return 黑白名单信息
     */
    public List<String> getList(String type, String num) {
        Query query = new Query(Criteria.where("mobNum").is(num));
        BWListModel bwListModel = mongoTemplate.findOne(query, BWListModel.class);
        if (bwListModel != null) {
            List<String> list;
            if ("black".equals(type)) {
                list = bwListModel.getBlackList();
            } else {
                list = bwListModel.getWhiteList();
            }
            return list;
        } else {
            throw new NullPointerException("该号码未注册黑白名单...");
        }
    }

    public void clearList(String type, String mobNum) {
        Query query = new Query(Criteria.where("mobNum").is(mobNum));
        List<String> list = new ArrayList<>();
        if ("black".equals(type)) {
            list.add("10000");
        } else {
            list.add("10086");
        }
        Update update = new Update();
        update.set(type + "List", list);
        mongoTemplate.updateFirst(query, update, BWListModel.class);
    }

    /**
     * 该方法验证主叫号码是否在被叫号码的黑名单中
     * @param type black or white
     * @param from 主叫号码
     * @param to 被叫号码
     * @return true or false
     */
    public boolean verifyNum(String type, String from, String to) {
        Query query = new Query(Criteria.where("mobNum").is(to));
        BWListModel bwListModel = mongoTemplate.findOne(query, BWListModel.class);
        if (bwListModel != null) {
            if ("black".equals(type)) {
                return bwListModel.getBlackList().contains(from);
            } else {
                return bwListModel.getWhiteList().contains(from);
            }
        } else {
            throw new NullPointerException("该号码未注册黑白名单...");
        }
    }

    private List<String> addUnique(List<String> list, String s) {
        if (list == null) {
            list = new ArrayList<>();
            list.add(s);
        } else {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        return list;
    }
}
