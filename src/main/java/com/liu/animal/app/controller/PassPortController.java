package com.liu.animal.app.controller;

import com.liu.animal.app.service.UserService;
import com.liu.animal.base.util.JwtUtil;
import com.liu.animal.base.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PassPortController
 * @Description: 认证中心的控制器
 * @Author: Admin
 * @Date 2020/5/4 14:52
 **/
@Controller
public class PassPortController {
    @Autowired
    private UserService userService;

    //生成token
    public static String getToken(HttpServletRequest request, HttpServletResponse response, String memberId, String nickname) {
        String token = null;
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("memberId", memberId);//rpc的主键返回策略失效
        userMap.put("nickname", nickname);
        //使用了nginx
        // Nginx代理转发的ip
        String ip = request.getHeader("x-forwarded-for");//通过nginx转发的客户端的ip
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();//从request中获取ip
            if (StringUtils.isBlank(ip)) {
                ip = "127.0.0.1";
            }
        }
        ip = MD5Util.encodeByMD5(ip);
        //需要按照设计的算法对参数进行加密后，生成token
        token = JwtUtil.encode("animals2018", userMap, ip);
        return token;
    }
}