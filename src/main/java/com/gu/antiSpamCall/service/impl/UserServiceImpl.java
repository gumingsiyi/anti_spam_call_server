package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.UserDao;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public AdminUser queryUserByNameAndPwd(String username, String password) {
        return userDao.findByNameAndPwd(username, password);
    }

    @Override
    public AdminUser queryUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
