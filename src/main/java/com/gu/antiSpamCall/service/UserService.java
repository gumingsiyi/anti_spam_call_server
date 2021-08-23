package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.model.AdminUser;

public interface UserService {
    AdminUser queryUserByNameAndPwd(String username, String password);
    AdminUser queryUserByName(String username);
    Boolean blackListAdd(String from, String to);
    Boolean blackListRemove(String from, String to);
    Boolean whiteListAdd(String from, String to);
    Boolean whiteListRemove(String from, String to);
    Boolean blackListClear(String mobNum);
    Boolean whiteListClear(String mobNum);
    Boolean verifyInWhiteList(String from, String to);
    Boolean verifyInBlackList(String from, String to);
}
