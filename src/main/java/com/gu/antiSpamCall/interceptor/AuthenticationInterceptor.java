package com.gu.antiSpamCall.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.gu.antiSpamCall.annotation.PassToken;
import com.gu.antiSpamCall.model.AdminUser;
import com.gu.antiSpamCall.service.UserService;
import com.gu.antiSpamCall.util.Result;
import com.gu.antiSpamCall.util.ResultCodeEnum;
import com.gu.antiSpamCall.util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 登录验证拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;

    void setErrorResponseMsg(HttpServletResponse response, String message) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        Result<Object> result = Result.code(ResultCodeEnum.NOT_LOGGED).setMsg(message);
        String jsonStr = JSON.toJSONString(result);
        out.append(jsonStr);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //若有跳过验证的注解
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //进行token验证
        if (token == null) {
            setErrorResponseMsg(response, "not logged error");
            return false;
        }
        String username;
        try {
            username = JWT.decode(token).getClaim("username").asString();
        } catch (JWTDecodeException e) {
            setErrorResponseMsg(response, "token error");
            return false;
        }

        AdminUser user = userService.queryUserByName(username);
        if (user == null) {
            setErrorResponseMsg(response, "token error");
            return false;
        }
        try {
            if (TokenUtil.VerifyByJWT(token, user.getPassword())) {
                return true;
            } else {
                setErrorResponseMsg(response, "token error");
                return false;
            }
        } catch (Exception e) {
            setErrorResponseMsg(response, "token error");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
