package com.liu.animal.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liu.animal.app.entity.UserBusinessLicense;
import com.liu.animal.app.mapper.UserBusinessLicenseMapper;
import com.liu.animal.app.service.UserBusinessLicenseService;
import com.liu.animal.base.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserBusinessLicenseServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/3 22:40
 **/
@Service
@Transactional
@Slf4j
public class UserBusinessLicenseServiceImpl implements UserBusinessLicenseService {
    @Autowired
    private UserBusinessLicenseMapper userBusinessLicenseMapper;

    @Override
    public List<UserBusinessLicense> findAll(Integer page, Integer num) {
        List<UserBusinessLicense> userBusinessLicenses = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userBusinessLicenseMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userBusinessLicenses = userBusinessLicenseMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("查询全部的 商业执照信息出错" + e.getMessage());
            return null;
        }
        return userBusinessLicenses;
    }

    @Override
    public int addInfo(UserBusinessLicense userBusinessLicense) {
        try {
            return userBusinessLicenseMapper.insertInfo(userBusinessLicense);
        } catch (Exception e) {
            log.error("保存营业执照信息出现错误" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(UserBusinessLicense userBusinessLicense) {
        try {
            return userBusinessLicenseMapper.updateById(userBusinessLicense);
        } catch (Exception e) {
            log.error("UserBusinessLicenseServiceImpl的updateById 出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userBusinessLicenseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserBusinessLicenseServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserBusinessLicense findById(String id) {
        UserBusinessLicense userBusinessLicense = null;
        try {
            userBusinessLicense = userBusinessLicenseMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserBusinessLicenseServiceImpl的UserBusinessLicense出错" + e.getMessage());
            return null;
        }
        return userBusinessLicense;
    }

    @Override
    public String businessLicense(UserBusinessLicense userBusinessLicense) {
        String image = userBusinessLicense.getImage();
        // 官网获取的 API Key 更新为你注册的
        String clientId = "7PMOmhTKrGbaz2P1TW3v4s4M";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "GBUm1ODwI04Ml9sjWwgtGC7g8Svw1QbQ";
        String accessToken = GetTokenUtil.getAuth(clientId, clientSecret);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";
        try {
            String imgParam = URLEncoder.encode(image, "UTF-8");
            String param = "image=" + imgParam;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("words_result")) {
                String words_result = jsonObject.getString("words_result");
                JSONObject object = JSONObject.parseObject(words_result);
                String workName = GetJsonUtil.getJsonValue(object, "单位名称", "words");
                if(workName == null || workName.equals("无")) {
                    return "你的营业执照有问题,没有单位名称";
                }
                userBusinessLicense.setWorkName(workName);
                String type = GetJsonUtil.getJsonValue(object, "类型", "words");
                userBusinessLicense.setType(type);
                String username = GetJsonUtil.getJsonValue(object, "法人", "words");
                if(username == null || username.equals("无")){
                    return "你的营业执照有问题,没有法人代表";
                }
                userBusinessLicense.setUsername(username);
                String address = GetJsonUtil.getJsonValue(object, "地址", "words");
                userBusinessLicense.setAddress(address);
                String effectiveTime = GetJsonUtil.getJsonValue(object, "有效期", "words");
                if (effectiveTime == null || effectiveTime.equals("无")) {
                    return "营业执照有误,没有有效期";
                }
                Date date = DateUtils.StringToDate(effectiveTime, "yyyy年MM月dd日");
                userBusinessLicense.setEffectiveTime(date);
                String certificateNumber = GetJsonUtil.getJsonValue(object, "证件编号", "words");
                userBusinessLicense.setCertificateNumber(certificateNumber);
                MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(image);
                if (multipartFile != null) {
                    String image1 = FileToUrlUtil.uploadImage(multipartFile);
                    userBusinessLicense.setBusinessLicensePhoto(image1);
                }
                if (StringUtils.isEmpty(username) && StringUtils.isEmpty(certificateNumber)) {
                    return "你的营业执照有问题";
                }
                Date begianTime = new Date();
                int res = DateUtils.compareDate(begianTime, date);
                if (res < 0) {
                    return "你的营业执照已过期";
                }
                if(!userBusinessLicense.getUsername().equals(username)){
                    return "法人代表姓名出错";
                }
            }
            userBusinessLicenseMapper.insertInfo(userBusinessLicense);
        } catch (Exception e) {
            log.error("营业执照出现问题" + e.getMessage());
            return "fail";
        }
        return "fail";
    }

    @Override
    public String count() {
        try {
            return userBusinessLicenseMapper.count();
        } catch (Exception e) {
            log.error("UserBusinessLicenseServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}