package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.UserInfoMapper;
import com.liu.animal.app.service.*;
import com.liu.animal.base.util.CpachaUtil;
import com.liu.animal.base.util.GetJsonUtil;
import com.liu.animal.base.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/2 16:29
 **/
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private UserBusinessLicenseService userBusinessLicenseService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private ArticleInfoService articleInfoService;

    @Override
    public List<UserInfo> findAll(Integer page, Integer num) {
        List<UserInfo> userInfos = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userInfos = userMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserServiceImpl的List<UserInfo> 失败" + e.getMessage());
            return null;
        }
        return userInfos;
    }

    @Override
    public int addInfo(UserInfo userInfo) {
        try {
            return userMapper.insertInfo(userInfo);
        } catch (Exception e) {
            log.error("UserServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(UserInfo userInfo) {
        String password = userInfo.getPassword();
        if (password != null) {
            password = MD5Util.encodeByMD5(password);
        }
        userInfo.setPassword(password);
        try {
            return userMapper.updateById(userInfo);
        } catch (Exception e) {
            log.error("UserServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = null;
        UserAuthentication userAuthentication = userAuthenticationService.findById(id);
        if (userAuthentication != null) {
            if (userAuthentication.getIsUpdate().equals("0")) {
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setIsAuthentication("1");
                userInfo1.setId(id);
                UserAuthentication userAuthentication1 = new UserAuthentication();
                userAuthentication1.setIsUpdate("1");
                userAuthentication1.setUserInfoId(id);
                userAuthenticationService.updateById(userAuthentication1);
                userService.updateById(userInfo1);
            }
        }
        UserBusinessLicense userBusinessLicense = userBusinessLicenseService.findById(id);
        if (userBusinessLicense != null) {
            if (userBusinessLicense.getIsUpdate().equals("0")) {
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setId(id);
                userInfo1.setIsPersonal("1");
                UserBusinessLicense userBusinessLicense1 = new UserBusinessLicense();
                userBusinessLicense1.setIsUpdate("1");
                userBusinessLicense1.setUserInfoId(id);
                userBusinessLicenseService.updateById(userBusinessLicense1);
                userService.updateById(userInfo1);
            }
        }
        try {
            userInfo = userMapper.selectById(id);
        } catch (Exception e) {
            log.error("findById方法出错" + e.getMessage());
            return null;
        }
        return userInfo;
    }

    @Override
    public UserInfo findByUsername(String username) {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.selectByUsername(username);
        } catch (Exception e) {
            log.error("findByUsername出错" + e.getMessage());
            return null;
        }
        return userInfo;
    }

    @Override
    public UserInfo findByPhone(String phone) {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.selectByPhone(phone);
        } catch (Exception e) {
            log.error("findByPhone方法出错" + e.getMessage());
            return null;
        }
        return userInfo;
    }


    @Override
    public UserInfo findByEmail(String email) {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.selectByEmail(email);
        } catch (Exception e) {
            log.error("findByEmail方法出错" + e.getMessage());
            return null;
        }
        return userInfo;
    }


    //    public UserInfo addRedis(String str){
//        String key = str;
//        Jedis jedis = redisUtil.getJedis();
//        if (jedis.get(key) != null) {
//            String json = jedis.get(key);
//            UserInfo userInfo1 = JSONObject.parseObject(json, UserInfo.class);
//            jedis.close();
//            return userInfo1;
//        } else {
//            jedis.setex(key, 60 * 60 * 24 * 7, str);
//        }
//        return null;
//    }
    @Override
    public String loginByUsername(String username, String password) {
        UserInfo userInfo = null;
        String passWord = MD5Util.encodeByMD5(password);
        try {
            userInfo = userMapper.selectByUsername(username);
        } catch (Exception e) {
            log.error("loginByUsername通过用户名查询用户信息出错" + e.getMessage());
            return "fail";
        }
        try {
            if (userInfo != null) {
                if (userInfo.getUsername().equals(username)) {
                    if (userInfo.getPassword().equals(passWord)) {
                        return "success";
                    } else {
                        return "该用户密码错误";
                    }
                } else {
                    return "该用户的用户名有误";
                }
            }
            return "该用户名不存在";
        } catch (Exception e) {
            log.error("用户名登录出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public String phoneCode(String phone) {
        if (phone != null) {
            String code = CpachaUtil.generatorVCode();
            try {
                String phoneSuccess = GetJsonUtil.getPhoneSuccess(code, phone);
                if (!phoneSuccess.equals("发送成功")) {
                    return phoneSuccess;
                }
                return code;
            } catch (Exception e) {
                log.error("手机验证码出现问题" + e.getMessage());
                return "fail";
            }
        }
        return "fail";
    }

    @Override
    public String checkPhone(String phone) {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.selectByPhone(phone);
        } catch (Exception e) {
            log.error("phoneCode通过手机查询用户信息出错" + e.getMessage());
            return "fail";
        }
        return userInfo != null ? "fail" : "success";
    }

    @Override
    public String loginByPhone(String username, String password) {
        UserInfo userInfo = null;
        String passWord = MD5Util.encodeByMD5(password);
        try {
            userInfo = userMapper.selectByPhone(username);
        } catch (Exception e) {
            log.error("loginByUsername通过手机号查询用户信息出错" + e.getMessage());
            return "fail";
        }
        try {
            if (userInfo != null) {
                if (userInfo.getPhone().equals(username)) {
                    if (userInfo.getPassword().equals(passWord)) {
                        return "success";
                    } else {
                        return "该用户密码错误";
                    }
                } else {
                    return "该用户的手机号有误";
                }
            }
            return "该用户名不存在";
        } catch (Exception e) {
            log.error("手机号登录出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public String loginByEmail(String username, String password) {
        UserInfo userInfo = null;
        String passWord = MD5Util.encodeByMD5(password);
        try {
            userInfo = userMapper.selectByEmail(username);
        } catch (Exception e) {
            log.error("loginByUsername通过手机号查询用户信息出错" + e.getMessage());
            return "fail";
        }
        try {
            if (userInfo != null) {
                if (userInfo.getEmail().equals(username)) {
                    if (userInfo.getPassword().equals(passWord)) {
                        return "success";
                    } else {
                        return "该用户密码错误";
                    }
                } else {
                    return "该用户的邮箱有误";
                }
            }
            return "该该邮箱不存在";
        } catch (Exception e) {
            log.error("邮箱登录出错" + e.getMessage());
            return "fail";
        }

    }

    @Override
    public String emailCode(String email) {
        String code = CpachaUtil.generatorVCode();
        try {
            String emailSuccess = GetJsonUtil.getEmailSuccess(email,code);
            if (!emailSuccess.equals("fail")) {
                return code;
            }
        } catch (Exception e) {
            log.error("邮箱验证码出错" + e.getMessage());
        }
        return "fail";
    }

    @Override
    public String register(UserInfo userInfo) {
        try {
            String password = userInfo.getPassword();
            userInfo.setPassword(MD5Util.encodeByMD5(password));
            userMapper.insertInfo(userInfo);
        } catch (Exception e) {
            log.error("注册保存信息出错" + e.getMessage());
            return "fail";
        }
        return "success";
    }

    @Override
    public List<UserInfo> findFollow(String userId) {
        List<UserInfo> userInfoList = new ArrayList<>();
        List<UserFollow> userFollowList = null;
        try {
            userFollowList = userMapper.selectFollow(userId);
        } catch (Exception e) {
            log.error("UserServiceImpl的selectFollow出错" + e.getMessage());
        }
        for (UserFollow userFollow : userFollowList) {
            UserInfo userInfo = userService.findById(userFollow.getFollowUserId());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public UserInfo findEndInfo() {
        try {
            return userMapper.selectEndInfo();
        } catch (Exception e) {
            log.error("UserServiceImpl的findEndInfo出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserInfo> findFan(String id) {
        List<UserInfo> userInfoList = new ArrayList<>();
        List<UserFollow> userFollowList;
        try {
            userFollowList = userMapper.selectFan(id);
        } catch (Exception e) {
            log.error("UserServiceImpl的findFan出错" + e.getMessage());
            return null;
        }
        for (UserFollow userFollow : userFollowList) {
            UserInfo userInfo = userService.findById(userFollow.getFanUserId());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public String checkEmail(String email) {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.selectByEmail(email);
        } catch (Exception e) {
            log.error("checkEmail通过邮箱查询用户信息出错" + e.getMessage());
            return "fail";
        }
        return userInfo == null ? "success" : "fail";
    }

    @Override
    public List<PetInfo> findPet(String userId) {
        return petInfoService.findInfoByUserInfoId(userId);
    }

    @Override
    public List<ArticleInfo> findArticleByUserId(String userId) {
        return articleInfoService.findArticleByCreateUserId(userId);
    }

    //    public String authentication(@RequestBody UserAuthentication userAuthentication) {
//        String image = userAuthentication.getIdCardPhoto();//身份证照片
//        try {
//            UserAuthentication userAuthentication1 = new UserAuthentication();
//            String msg = idcard(image, userAuthentication1);
//            if (!msg.equals("success")) {
//                return msg;
//            }
//            if (userAuthentication != null) {
//                if (!userAuthentication1.getUsername().equals(userAuthentication.getUsername())) {
//                    return "你输入的姓名和身份证不符合,请重新输入";
//                }
//                if (!userAuthentication.getAddress().equals(userAuthentication1.getAddress())) {
//                    return "你输入的地址和身份证不符合,请重新输入";
//                }
//                userAuthentication.setUsername(userAuthentication1.getUsername());
//                userAuthentication.setBirthday(userAuthentication1.getBirthday());
//                userAuthentication.setSex(userAuthentication1.getSex());
//                userAuthentication.setIdNumber(userAuthentication1.getIdNumber());
//                userAuthentication.setAddress(userAuthentication1.getAddress());
//                userAuthentication.setNation(userAuthentication1.getNation());
//                MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(image);
//                String image1 = FileToUrlUtil.uploadImage(multipartFile);
//                userAuthentication.setIdCardPhoto(image1);
//            } else {
//                return "你输入的参数有误";
//            }
//            userAuthenticationService.addInfo(userAuthentication1);
//            return "success";
//        } catch (Exception e) {
//            System.out.println("实名认证出现问题");
//            return "fail";
//        }
//    }
    @Override
    public String count() {
        try {
            return userMapper.count();
        } catch (Exception e) {
            log.error("UserServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

}