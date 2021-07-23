package com.gu.antiSpamCall.dto.response;

import lombok.Data;

/**
 * 登录结果，true登录成功，false登录失败
 */

@Data
public class LoginResponse {
    private boolean result;
    private String message;
    private String username;
    private String token;
    private String nickname;
}
