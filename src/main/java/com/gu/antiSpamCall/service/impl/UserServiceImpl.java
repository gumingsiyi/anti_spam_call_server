package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.BWListDao;
import com.gu.antiSpamCall.dao.UserDao;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Resource
    BWListDao bwListDao;

    @Override
    public AdminUser queryUserByNameAndPwd(String username, String password) {
        return userDao.findByNameAndPwd(username, password);
    }

    @Override
    public AdminUser queryUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public Boolean blackListAdd(String from, String to) {
        try {
            bwListDao.editList("add", "black", from, to);
            log.info(String.format("[%s] 添加到 [%s] 的黑名单", from, to));
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean blackListRemove(String from, String to) {
        try {
            bwListDao.editList("remove", "black", from, to);
            log.info(String.format("[%s] 移除 [%s] 的黑名单", from, to));
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean whiteListAdd(String from, String to) {
        try {
            bwListDao.editList("add", "white", from, to);
            log.info(String.format("[%s] 添加到 [%s] 的白名单", from, to));
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean whiteListRemove(String from, String to) {
        try {
            bwListDao.editList("remove", "white", from, to);
            log.info(String.format("[%s] 移除 [%s] 的黑名单", from, to));
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean blackListClear(String mobNum) {
        return null;
    }

    @Override
    public Boolean whiteListClear(String mobNum) {
        return null;
    }

    @Override
    public Boolean verifyInWhiteList(String from, String to) {
        try {
            return bwListDao.verifyNum("white", from, to);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean verifyInBlackList(String from, String to) {
        try {
            return bwListDao.verifyNum("black", from, to);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }


}
