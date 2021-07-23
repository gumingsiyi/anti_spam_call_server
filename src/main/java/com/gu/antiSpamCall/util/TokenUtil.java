package com.gu.antiSpamCall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gu.antiSpamCall.model.AdminUser;

import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME= 15*24*60*60*1000;
    private static final String TOKEN_SECRET="ANTISPAMCALL";  //密钥盐

    /**
     * 生成签名
     * @param user 管理员信息
     * @return String token
     */
    public static String createSignByJWT(AdminUser user) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withClaim("username", user.getUsername())
                    .sign(Algorithm.HMAC256(user.getPassword()+TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token 传入token
     * @param password 对应的用户的密码
     * @return 验证成功true
     */
    public static boolean VerifyByJWT(String token, String password){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(password+TOKEN_SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
