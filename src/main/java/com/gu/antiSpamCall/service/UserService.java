package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.model.AdminUser;

public interface UserService {
    AdminUser queryUserByNameAndPwd(String username, String password);
    AdminUser queryUserByName(String username);
}
