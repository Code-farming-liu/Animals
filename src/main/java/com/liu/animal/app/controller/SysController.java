package com.liu.animal.app.controller;


import com.liu.animal.app.entity.UserAuthentication;
import com.liu.animal.app.entity.UserBusinessLicense;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.service.SignService;
import com.liu.animal.app.service.UserAuthenticationService;
import com.liu.animal.app.service.UserBusinessLicenseService;
import com.liu.animal.app.service.UserService;
import com.liu.animal.base.util.FileToUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: SysController
 * @Description: 系统
 * @Author: Admin
 * @Date 2020/5/3 11:24
 **/
@Controller
@RequestMapping("sys")
public class SysController {
    @Autowired
    private UserService userService;
    @Autowired
    private SignService signService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private UserBusinessLicenseService userBusinessLicenseService;

    /**
     * @param multipartFile
     * @return java.lang.String
     * @Author Admin
     * @Description 文件上传
     * @Date 11:27 2020/5/3
     **/
    @PostMapping("file-up-load")
    @ResponseBody
    public String fileUpLoad(@RequestParam("file") MultipartFile multipartFile) {
        if(multipartFile == null){
            return "空指针,检查参数";
        }
        String imaUrl = FileToUrlUtil.uploadImage(multipartFile);
        return imaUrl;
    }
    /**
     * @Author Admin
     * @Description 寻找实名认证的用户信息
     * @Date 17:07 2020/5/20
     * @param userId
     * @return com.liu.animal.app.entity.UserAuthentication
     **/
    @GetMapping("find-authentication")
    @ResponseBody
    public UserAuthentication findAuthentication(String userId) {
        return userAuthenticationService.findById(userId);
    }
    /**
     * @Author Admin
     * @Description 查找对应的商业执照信息
     * @Date 17:08 2020/5/20
     * @param userId
     * @return com.liu.animal.app.entity.UserBusinessLicense
     **/
    @GetMapping("find-business")
    @ResponseBody
    public UserBusinessLicense findBusiness(String userId) {
        return userBusinessLicenseService.findById(userId);
    }
    /**
     * @Author Admin
     * @Description 根据id查找信息
     * @Date 17:08 2020/5/20
     * @param userId
     * @return com.liu.animal.app.entity.UserInfo
     **/
    @GetMapping("find-by-user-id")
    @ResponseBody
    public UserInfo findByUserId(String userId) {
        return userService.findById(userId);
    }
    /**
     * @Author Admin
     * @Description 通过手机号注册
     * @Date 17:09 2020/5/20
     * @param phone
     * @return java.lang.String
     **/
    @GetMapping("register-by-phone")
    @ResponseBody
    public String registerByPhone(String phone) {
        return userService.phoneCode(phone);
    }

    /**
     * @param
     * @return java.lang.String
     * @Author Admin
     * @Description 检查邮箱是否重名
     * @Date 16:03 2020/5/13
     **/
    @GetMapping("check-email")
    @ResponseBody
    public String checkEmail(String email) {
        return userService.checkEmail(email);
    }
    /**
     * @Author Admin
     * @Description 用户名查重
     * @Date 17:10 2020/5/20
     * @param username
     * @return java.lang.String
     **/
    @GetMapping("register-by-username")
    @ResponseBody
    public String registerByUsername(String username) {
        UserInfo userInfo = userService.findByUsername(username);
        if (userInfo != null) {
            if (userInfo.getUsername().equals(username)) {
                return "该用户名已经存在";
            }
        }
        return "success";
    }
    /**
     * @Author Admin
     * @Description 查重手机号
     * @Date 17:10 2020/5/20
     * @param phone
     * @return java.lang.String
     **/
    @GetMapping("check-phone")
    @ResponseBody
    public String checkPhone(String phone) {
        return userService.checkPhone(phone);
    }
    /**
     * @Author Admin
     * @Description 发送邮件
     * @Date 17:10 2020/5/20
     * @param email
     * @return java.lang.String
     **/
    @GetMapping("send-by-email")
    @ResponseBody
    public String sendByEmail(String email) {
        return userService.emailCode(email);
    }


    //    /**
//     * @param code
//     * @return java.lang.String
//     * @Author Admin
//     * @Description 验证码的比较
//     * @Date 2020/5/4
//     **/
//    @PostMapping("compare-code")
//    @ResponseBody
//    public String compareCode(String code) {
//        if (code.equals(this.code)) {
//            this.code = "";
//            return "success";
//        }
//        return "fail";
//    }

}