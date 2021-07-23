package com.gu.antiSpamCall.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adminUserInfo")
@Data
public class AdminUser {
    private String nickname;
    private String username;
    private String password;
}
