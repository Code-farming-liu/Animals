package com.liu.animal.app.controller;

import com.liu.animal.app.entity.SysSign;
import com.liu.animal.app.service.SignService;
import com.liu.animal.base.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName: SignController
 * @Description: 签到
 * @Author: Admin
 * @Date 2020/5/5 11:45
 **/
@Controller
@Slf4j
@RequestMapping("sign")
public class SignController {
    @Autowired
    private SignService signService;
    /**
     * @Author Admin
     * @Description 签到
     * @Date 17:06 2020/5/20
     * @param userId
     * @return java.lang.String
     **/
    @GetMapping("do-sign")
    @ResponseBody
    public String doSign(String userId) {
        return signService.sgin(userId);
    }
    /**
     * @Author Admin
     * @Description 补签
     * @Date 7:54 2020/5/12
     * @param sysSign
     * @return java.lang.String
     **/
    @PostMapping("do-sign-ature")
    @ResponseBody
    public String signAture(@RequestBody SysSign sysSign){
        return signService.signAture(sysSign);
    }
    /**
     * @Author Admin
     * @Description 获得今天是周几
     * @Date 17:06 2020/5/20
     * @param
     * @return java.lang.String
     **/
    @GetMapping("get-week")
    @ResponseBody
    public String getWeek() {
        try {
            return DateUtils.dayForWeek(new Date());
        } catch (Throwable throwable) {
            log.error("SignController的getWeek方法出错");
            return null;
        }
    }
    /**
     * @Author Admin
     * @Description 获取该用户签到的全部信息
     * @Date 17:07 2020/5/20
     * @param userId
     * @return com.liu.animal.app.entity.SysSign
     **/
    @GetMapping("find-by-user-id")
    @ResponseBody
    public SysSign findByUserId(String userId){
        return signService.findByUserId(userId);
    }
}