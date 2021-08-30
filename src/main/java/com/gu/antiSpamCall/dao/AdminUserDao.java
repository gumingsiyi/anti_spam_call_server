package com.gu.antiSpamCall.dao;

import com.gu.antiSpamCall.model.AdminUser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户的DAO
 */
@Component
public class AdminUserDao {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 查询对象
     */
    public AdminUser findUserByName(String name) {
        Query query = new Query(Criteria.where("username").is(name));
        return mongoTemplate.findOne(
                query,
                AdminUser.class);
    }

    /**
     * 根据用户名和密码查询
     */
    public AdminUser findByNameAndPwd(String username, String password){
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        return mongoTemplate.findOne(
                query,
                AdminUser.class);
    }
}
