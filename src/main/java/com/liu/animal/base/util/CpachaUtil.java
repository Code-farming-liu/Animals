package com.liu.animal.base.util;

/**
 * ClassName: CpachaUtil
 * Description: 验证码生成器
 * date: 2019/11/20 10:52
 *
 * @author 刘瑞
 * @since JDK 1.8
 */

import com.alibaba.fastjson.JSONObject;

import java.util.Random;

/**
 * 验证码生成器
 *
 * @author llq
 */
public class CpachaUtil {
    private static final char[] code = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    /**
     * 验证码长度
     * 默认4个字符
     */
    private static final int vcodeLen = 6;

    public static String generatorVCode() {
        int len = code.length;
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vcodeLen; i++) {
            int index = ran.nextInt(len);
            sb.append(code[index]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "{\"code\":0,\"data\":\"发送成功\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        if(jsonObject.containsKey("data")){
            String success = jsonObject.getString("data");
            System.out.println(success);
        }
    }

}
