package com.liu.animal.app.interceptors;

import com.alibaba.fastjson.JSON;
import com.liu.animal.app.annotations.LoginRequired;
import com.liu.animal.base.util.CookieUtil;
import com.liu.animal.base.util.HttpclientUtil;
import com.liu.animal.base.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AuthInterceptor
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/4 21:13
 **/

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截代码
        //判断被拦截的请求的访问的方法的注解（是否是需要拦截的）
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //根据反射获取方法上的注解
        LoginRequired methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequired.class);
        //是否拦截
        if (methodAnnotation == null) {
            return true;
        }

        String token = "";

        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        if (StringUtils.isNotBlank(oldToken)) {
            //老token不空
            token = oldToken;
        }
        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)) {
            //新token不空
            token = newToken;
        }
        //是否登录
        boolean loginSuccess = methodAnnotation.loginSuccess();//获得该请求是否必须登录成功
        //调用认证中心进行验证
        String success = "fail";
        Map<String, String> successMap = new HashMap<>();
        if (StringUtils.isNotBlank(token)) {
            String ip = request.getHeader("x-forwarded-for");//通过nginx转发的客户端的ip
            if (StringUtils.isBlank(ip)) {
                ip = request.getRemoteAddr();//从request中获取ip
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
            }
            ip = MD5Util.encodeByMD5(ip);
            String successJson = HttpclientUtil.doGet("http://localhost:9999/verify?token=" + token + "&currentIp=" + ip);
            successMap = JSON.parseObject(successJson, Map.class);

            success = successMap.get("status");
        }

        if (loginSuccess) {
            //必须登录成功才能使用
            if (!success.equals("success")) {
                //重定向回passport
                StringBuffer requestURL = request.getRequestURL();
                response.sendRedirect("http://passport.gmall.com:8085/index?ReturnUrl=" + requestURL);
                return false;
            }
            //需要将token携带的用户信息写入
            request.setAttribute("memberId", successMap.get("memberId"));
            request.setAttribute("nickname", successMap.get("nickname"));
            //验证通过，覆盖cookie中的token
            if (StringUtils.isNotBlank(token)) {
                //两小时过期
                CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60 * 2, true);
            }

        } else {
            //可以不登录访问,但是必须验证
            if (success.equals("success")) {
                //需要将token携带的用户信息写入
                request.setAttribute("memberId", successMap.get("memberId"));
                request.setAttribute("nickname", successMap.get("nickname"));
                //验证通过，覆盖cookie中的token
                if (StringUtils.isNotBlank(token)) {
                    //两小时过期
                    CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60 * 2, true);
                }
            }

        }
        return true;
    }
}