package com.liu.animal.base.util;

import java.security.MessageDigest;

/**
 * ClassName: MD5Util
 * Description:
 * date: 2020/2/9 14:51
 *
 * @author 刘瑞
 * @since JDK 1.8
 */
public class MD5Util {
    public static String encodeByMD5(String inStr) {
       MessageDigest md5 = null;
       try {
          md5 = MessageDigest.getInstance("MD5");
       } catch (Exception e) {
          System.out.println("MD5工具类加密出错");
          return "";
       }
       char[] charArray = inStr.toCharArray();
       byte[] byteArray = new byte[charArray.length];
       for (int i = 0; i < charArray.length; i++) {
          byteArray[i] = (byte) charArray[i];
       }
       byte[] md5Bytes = md5.digest(byteArray);
       StringBuffer hexValue = new StringBuffer();
       for (int i = 0; i < md5Bytes.length; i++) {
          int val = ((int) md5Bytes[i]) & 0xff;
          if(val < 16){
             hexValue.append("0");
          }
          hexValue.append(Integer.toHexString(val));
       }
       return hexValue.toString();
    }
    //解密算法
   public static String decodeByMD5(String inStr){
      char[] a = inStr.toCharArray();
      for (int i = 0; i < a.length; i++) {
         a[i] = (char)(a[i] ^ 't');
      }
      String s = new String(a);
      char[] b = s.toCharArray();
      for (int i = 0; i < b.length; i++) {
         b[i] = (char)(b[i] ^ 't');
      }
      String c = new String(b);
      return c;
   }

   public static void main(String[] args) {
      String str = "12345";
      System.out.println(encodeByMD5(str));
      System.out.println(decodeByMD5(str));
   }
}

