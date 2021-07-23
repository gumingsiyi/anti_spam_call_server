package com.gu.antiSpamCall.service;

import com.gu.antiSpamCall.dto.request.LoginRequest;
import com.gu.antiSpamCall.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse adminLogin(LoginRequest request);
}
