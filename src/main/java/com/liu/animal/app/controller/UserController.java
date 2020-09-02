package com.liu.animal.app.controller;


import com.liu.animal.app.entity.*;
import com.liu.animal.app.service.UserAuthenticationService;
import com.liu.animal.app.service.UserBusinessLicenseService;
import com.liu.animal.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: test
 * @Description: 用户
 * @Author: Admin
 * @Date 2020/5/1 20:56
 **/
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private UserBusinessLicenseService userBusinessLicenseService;

//    @RequestMapping("test")
//    @ResponseBody
//    public List<UserInfo> test() {
//        List<UserInfo> userInfos = userService.findAll();
//        return userInfos;
//    }


    /**
     * @param userInfo
     * @return java.lang.String
     * @Author Admin
     * @Description //用户注册
     * @Date 20:25 2020/5/2
     **/
    @PostMapping("register")
    @ResponseBody
    public Map<String, Object> register(@RequestBody UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>();
        String success = userService.register(userInfo);
        if (success.equals("success")) {
            UserInfo userInfo1 = userService.findEndInfo();
            if (userInfo != null) {
                map.put("data", userInfo1);
            }
        }
        map.put("success", success);
        return map;
    }
    /**
     * @Author Admin
     * @Description 修改用户信息
     * @Date 17:15 2020/5/20
     * @param userInfo
     * @return java.lang.String
     **/
    @PostMapping("update-user-by-user-id")
    @ResponseBody
    public String updatePassword(@RequestBody UserInfo userInfo) {
        int res = userService.updateById(userInfo);
        if (res == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param userAuthentication
     * @return java.lang.String
     * @Author Admin
     * @Description 人脸和身份证对比
     * @Date 17:33 2020/5/3
     **/
    @PostMapping("face-math")
    @ResponseBody
    public String faceMatch(@RequestBody UserAuthentication userAuthentication) {
        return userAuthenticationService.faceMatch(userAuthentication);
    }


    /**
     * @param userAuthentication
     * @return java.lang.String
     * @Author Admin
     * @Description 获取身份证的信息
     * @Date 19:49 2020/5/3
     **/
    @PostMapping("authentication")
    @ResponseBody
    public String authentication(@RequestBody UserAuthentication userAuthentication) {
        return userAuthenticationService.authentication(userAuthentication);
    }


    /**
     * @param userBusinessLicense
     * @return java.lang.String
     * @Author Admin
     * @Description 营业执照的识别
     * @Date 20:19 2020/5/3
     **/
    @PostMapping("business-license")
    @ResponseBody
    public String businessLicense(@RequestBody UserBusinessLicense userBusinessLicense) {
        return userBusinessLicenseService.businessLicense(userBusinessLicense);
    }
    /**
     * @Author Admin
     * @Description 用户关注列表
     * @Date 17:16 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-follow")
    @ResponseBody
    public Map<String, Object> findFollowById(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<UserInfo> follows = userService.findFollow(userId);
        map.put("data", follows);
        map.put("count", follows.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 用户的粉丝列表
     * @Date 17:16 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-fan")
    @ResponseBody
    public Map<String, Object> findFanById(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<UserInfo> fans = userService.findFan(userId);
        map.put("data", fans);
        map.put("count", fans.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 查找用户的 宠物列表
     * @Date 17:17 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-pet")
    @ResponseBody
    public Map<String, Object> findPetByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<PetInfo> petInfos = userService.findPet(userId);
        map.put("data", petInfos);
        map.put("count", petInfos.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 查找用户的动态
     * @Date 17:17 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-article")
    @ResponseBody
    public Map<String, Object> findArticleByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<ArticleInfo> articleInfoList = userService.findArticleByUserId(userId);
        map.put("data", articleInfoList);
        map.put("count", articleInfoList.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 根据用户名 密码登录
     * @Date 17:18 2020/5/20
     * @param username
     * @param password
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("login-by-username")
    @ResponseBody
    public Map<String, Object> loginByUsername(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        String result = userService.loginByUsername(username, password);
        UserInfo userInfo = userService.findByUsername(username);
        if (result.equals("success")) {
            map.put("data", userInfo);
        }
        map.put("success", result);
        return map;
    }
    /**
     * @Author Admin
     * @Description 根据手机号登录
     * @Date 17:18 2020/5/20
     * @param username
     * @param password
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("login-by-phone")
    @ResponseBody
    public Map<String, Object> loginByPhone(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        String result = userService.loginByPhone(username, password);
        UserInfo userInfo = userService.findByPhone(username);
        if (result.equals("success")) {
            map.put("data", userInfo);
        }
        map.put("success", result);
        return map;
    }
    /**
     * @Author Admin
     * @Description 根据邮箱登录
     * @Date 17:18 2020/5/20
     * @param username
     * @param password
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("login-by-email")
    @ResponseBody
    public Map<String, Object> loginByEmail(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        String result = userService.loginByEmail(username, password);
        UserInfo userInfo = userService.findByEmail(username);
        if (result.equals("success")) {
            map.put("data", userInfo);
        }
        map.put("success", result);
        return map;
    }
    /**
     * @Author Admin
     * @Description 修改用户
     * @Date 17:18 2020/5/20
     * @param userInfo
     * @return java.lang.String
     **/
    @PostMapping("update-user")
    @ResponseBody
    public String updateUser(@RequestBody UserInfo userInfo) {
        int res = userService.updateById(userInfo);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除用户
     * @Date 17:19 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-user")
    @ResponseBody
    public String deleteUser(String id) {
        int res = userService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
}