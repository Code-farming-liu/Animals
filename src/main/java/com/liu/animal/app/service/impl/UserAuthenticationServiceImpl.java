package com.liu.animal.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liu.animal.app.entity.UserAuthentication;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.mapper.UserAuthenticationMapper;
import com.liu.animal.app.service.UserAuthenticationService;
import com.liu.animal.app.service.UserService;
import com.liu.animal.base.util.DateUtils;
import com.liu.animal.base.util.GetJsonUtil;
import com.liu.animal.base.util.GetTokenUtil;
import com.liu.animal.base.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassName: UserAuthenticationServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/3 15:39
 **/
@Service
@Transactional
@Slf4j
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private UserAuthenticationMapper userAuthenticationMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    public List<UserAuthentication> findAll(Integer page, Integer num) {
        List<UserAuthentication> userAuthentications = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userAuthenticationMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userAuthentications = userAuthenticationMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的findAll出错" + e.getMessage());
            return null;
        }
        return userAuthentications;
    }

    @Override
    public int addInfo(UserAuthentication userAuthentication) {
        try {
            userAuthenticationMapper.insertInfo(userAuthentication);
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的addInfo出错" + e.getMessage());
            return 0;
        }
        String email = userAuthentication.getEmail();
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setId(userAuthentication.getUserInfoId());
        int res1 = userService.updateById(userInfo);
        return res1 == 1 ? 1 : 0;
    }

    @Override
    public int updateById(UserAuthentication userAuthentication) {
        try {
            return userAuthenticationMapper.updateById(userAuthentication);
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userAuthenticationMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }

    }

    @Override
    public UserAuthentication findById(String id) {
        UserAuthentication userAuthentication = null;
        try {
            userAuthentication = userAuthenticationMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的findById出错" + e.getMessage());
        }
        return userAuthentication;
    }

    @Override
    public String faceMatch(UserAuthentication userAuthentication) {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "7PMOmhTKrGbaz2P1TW3v4s4M";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "GBUm1ODwI04Ml9sjWwgtGC7g8Svw1QbQ";
        String accessToken = GetTokenUtil.getAuth(clientId, clientSecret);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String image1 = userAuthentication.getLiveImage1();
            String image2 = userAuthentication.getIdCardImage2();

            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "CERT");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "LOW");

            images.add(map1);
            images.add(map2);

            String param = JSON.toJSONString(images);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            double score = 0;
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("error_msg")) {
                String errorMsg = jsonObject.getString("error_msg");
                if (!errorMsg.equals("SUCCESS")) {
                    return errorMsg;
                }
            }
            if (jsonObject.containsKey("result")) {
                String result1 = jsonObject.getString("result");
                JSONObject json = JSONObject.parseObject(result1);
                if (json.containsKey("score")) {
                    score = Double.parseDouble(json.getString("score"));
                }
            }
            return score >= 80 ? "success" : "fail";
        } catch (Exception e) {
            log.error("人脸比对出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public String authentication(UserAuthentication userAuthentication) {
        String image = userAuthentication.getImage();
        // 官网获取的 API Key 更新为你注册的
        String clientId = "mjB0kSGCPV19RkbAo3hoGBG3";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "YhovjR0vs9TXV2cgBAWywYlxznEBBBd9 ";
        String accessToken = GetTokenUtil.getAuth(clientId, clientSecret);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {

            String imgParam = URLEncoder.encode(image, "UTF-8");

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            String result = HttpUtil.post(url, accessToken, param);

            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.containsKey("image_status")) {
                String image_status = jsonObject.getString("image_status");
                if (!image_status.equals("normal")) {
                    return "身份证有误";
                }
            }
            if (jsonObject.containsKey("words_result")) {
                String words_result = jsonObject.getString("words_result");
                JSONObject object = JSON.parseObject(words_result);
                String username = GetJsonUtil.getJsonValue(object, "姓名", "words");
                userAuthentication.setUsername(username);
                String nation = GetJsonUtil.getJsonValue(object, "民族", "words");
                userAuthentication.setNation(nation);
                String address = GetJsonUtil.getJsonValue(object, "住址", "words");
                userAuthentication.setAddress(address);
                String idCard = GetJsonUtil.getJsonValue(object, "公民身份号码", "words");
                userAuthentication.setIdNumber(idCard);
                String birth = GetJsonUtil.getJsonValue(object, "出生", "words");
                Date date = DateUtils.StringToDate(birth, "yyyyMMdd");
                userAuthentication.setBirthday(date);
                String sex = GetJsonUtil.getJsonValue(object, "性别", "words");
                userAuthentication.setSex(sex);
                if (!userAuthentication.getUsername().equals(username)) {
                    return "该用户填写的姓名和身份证不符";
                }
                if (!userAuthentication.getIdNumber().equals(idCard)) {
                    return "该用户填写的身份证号和当前身份证号不符";
                }
            }
            userAuthenticationService.addInfo(userAuthentication);
            return "success";
        } catch (Exception e) {
            log.error("身份证解析出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public String count() {
        try {
            return userAuthenticationMapper.count();
        } catch (Exception e) {
            log.error("UserAuthenticationServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}