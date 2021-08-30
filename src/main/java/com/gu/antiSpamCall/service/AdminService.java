package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.CallInfoResponse;
import com.gu.antiSpamCall.dto.response.LoginResponse;

import java.util.List;

public interface AdminService {
    LoginResponse adminLogin(LoginRequest request);
    List<String> getBlackList(String num);
    List<String> getWhiteList(String num);
    CallInfoResponse queryCallInfo(String from, String to);
}
