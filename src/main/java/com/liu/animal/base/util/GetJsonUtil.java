package com.liu.animal.base.util;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GetJsonUtil
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/3 20:14
 **/

public class GetJsonUtil {
    /**
     * @param object
     * @param param3
     * @param param4
     * @return java.lang.String
     * @Author Admin
     * @Description 解析json
     * @Date 19:50 2020/5/3
     **/
    public static String getJsonValue(JSONObject object, String param3, String param4) {

        if (object.containsKey(param3)) {
            String objectString = object.getString(param3);
            JSONObject name = JSONObject.parseObject(objectString);
            if (name.containsKey(param4)) {
                String str = name.getString(param4);
                return str;
            }
        }
        return null;
    }

    /**
     * @param phoneCode
     * @param phone
     * @return java.lang.String
     * @Author Admin
     * @Description 解析短信是否发送成功
     * @Date 13:15 2020/5/4
     **/
    public static String getPhoneSuccess(String phoneCode, String phone) throws Exception {
        //发送短信
        String apiUrl = "https://sms_developer.zhenzikj.com";
        String appId = "105352";
        String appSecret = "660da3b7-f3fa-42e4-bac6-2b2ac1297a42";
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, String> map = new HashMap<>();
        map.put("message", "欢迎注册宠它，您正在注册验证，验证码为:" + phoneCode + "请在1分钟之内" +
                "按页面提交验证码，切勿将验证码泄露于他人");
        map.put("number", phone);
        String result = client.send(map);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.containsKey("data")) {
            String success = jsonObject.getString("data");
            return success;
        }
        return "fail";
    }

    public static String getEmailSuccess(String toUserEmail, String code) {
        //第一步
        //创建HtmlEmail实例对象
        HtmlEmail htmlEmail = new HtmlEmail();
        //设置邮箱的SMTP服务器，登录相对应的邮箱官网，去拿就行了
        //邮箱的SMTP服务器，一般123邮箱的是 smtp.123.com qq邮箱的是stmp.qq.com
        htmlEmail.setHostName("smtp.qq.com");
        //设置发送的字符集类型
        htmlEmail.setCharset("utf-8");

        //第二步
        //设置收件人
        try {
            htmlEmail.addTo(toUserEmail);
            //设置发送人的邮箱 和用户名（可以随便填写）
            htmlEmail.setFrom("2258076697@qq.com", "宠它");
            //设置邮箱的地址和授权码（自己设置开启pop3）
            htmlEmail.setAuthentication("2258076697@qq.com", "cjxiukpamsahdihf");
            //设置发送的标题
            htmlEmail.setSubject("animals验证码");
            htmlEmail.setSSLOnConnect(true);
            htmlEmail.setSslSmtpPort("465");
            //设置发送的内容
            htmlEmail.setMsg("您正在邮箱验证，验证码为:" + code + "请在1分钟之内按页面提交验证码，切勿将验证码泄露于他人");
            String send = htmlEmail.send();
            return send;
        } catch (EmailException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return "fail";
        }

    }

}