package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.BWListDao;
import com.gu.antiSpamCall.dao.CallDao;
import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.CallInfoResponse;
import com.gu.antiSpamCall.dto.response.LoginResponse;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.model.CallRecord;
import com.gu.antiSpamCall.service.AdminService;
import com.gu.antiSpamCall.service.UserService;
import com.gu.antiSpamCall.util.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserService userService;

    @Resource
    BWListDao bwListDao;

    @Resource
    CallDao callDao;

    @Override
    public LoginResponse adminLogin(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        AdminUser res = userService.queryUserByNameAndPwd(request.getUsername(), request.getPassword());
        if (res == null) {
            response.setResult(false);
            response.setMessage("用户名密码错误，登录失败。");
        } else {
            log.info(String.format("用户 [%s] 登录成功.", res.getUsername()));
            String token = TokenUtil.createSignByJWT(res);
            response.setResult(true);
            response.setUsername(res.getUsername());
            response.setNickname(res.getNickname());
            response.setToken(token);
            response.setMessage("登录成功。");
        }
        return response;
    }

    @Override
    public List<String> getBlackList(String num) {
        return bwListDao.getList("black", num);
    }

    @Override
    public List<String> getWhiteList(String num) {
        return bwListDao.getList("white", num);
    }

    @Override
    public CallInfoResponse queryCallInfo(String from, String to) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(now);
        CallRecord record = new CallRecord(null, from, to, time, -1, -1);
        return callDao.getCallInfo(record);
    }

    @Override
    public Boolean blackListAdd(String from, String to) {
        return userService.blackListAdd(from, to);
    }

    @Override
    public Boolean blackListRemove(String from, String to) {
        return userService.blackListRemove(from, to);
    }

    @Override
    public Boolean whiteListAdd(String from, String to) {
        return userService.whiteListAdd(from, to);
    }

    @Override
    public Boolean whiteListRemove(String from, String to) {
        return userService.whiteListRemove(from, to);
    }
}
