package com.liu.animal.app.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Admin
 * @Description 用户信息
 * @Date 10:43 2020/5/2
 * @return
 **/

public class UserInfo implements Serializable {
    private String id;
    private String username;
    private String nickName;
    private String isPersonal;
    private String isPersonalStr;
    private String password;
    private String phone;
    private String address;
    private Date birthday;
    private String sex;
    private String sexStr;
    private String city;
    private String job;
    private Integer integral;
    private String integralStr;
    private String vip;
    private String vipStr;
    private Date vipStartDate;
    private Date vipEndDate;
    private String photo;
    private String qq;
    private String wx;
    private String isAuthentication;
    private String isAuthenticationStr;
    private String email;
    private List<UserChat> chatList;
    private List<UserFollow> followList;
    private List<SysCoupon> couponList;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntegralStr() {
        if (integral != null) {
            if (integral <= 50) {
                integralStr = "萌宠小新";
            } else if (integral > 50 && integral <= 100) {
                integralStr = "萌宠小白";
            } else if (integral > 101 && integral <= 500) {
                integralStr = "萌宠达人";
            } else if (integral > 501 && integral <= 1000) {
                integralStr = "萌宠专家";
            } else if (integral > 1000) {
                integralStr = "萌宠大师";
            }
        }
        return integralStr;
    }

    public void setIntegralStr(String integralStr) {
        this.integralStr = integralStr;
    }

    public String getSexStr() {
        if (StringUtils.isNotEmpty(sex)) {
            if (sex.equals("0")) {
                sexStr = "女";
            } else if (sex.equals("1")) {
                sexStr = "男";
            }
        }
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getVipStr() {
        if (StringUtils.isNotEmpty(vip)) {
            if (vip.equals("1")) {
                vipStr = "会员";
            } else if (vip.equals("0")) {
                vipStr = "非会员";
            }
        }
        return vipStr;
    }

    public void setVipStr(String vipStr) {
        this.vipStr = vipStr;
    }

    public String getIsAuthenticationStr() {
        if (StringUtils.isNotEmpty(isAuthentication)) {
            if (isAuthentication.equals("1")) {
                isAuthenticationStr = "实名认证";
            } else if (isAuthentication.equals("0")) {
                isAuthenticationStr = "非实名认证";
            }
        }
        return isAuthenticationStr;
    }

    public void setIsAuthenticationStr(String isAuthenticationStr) {
        this.isAuthenticationStr = isAuthenticationStr;
    }

    public String getIsPersonalStr() {
        if (StringUtils.isNotEmpty(isPersonal)) {
            if (isPersonal.equals("1")) {
                isPersonalStr = "个人";
            } else if (isPersonal.equals("0")) {
                isPersonalStr = "非个人";
            }
        }
        return isPersonalStr;
    }

    public void setIsPersonalStr(String isPersonalStr) {
        this.isPersonalStr = isPersonalStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIsPersonal() {

        return isPersonal;
    }

    public void setIsPersonal(String isPersonal) {
        this.isPersonal = isPersonal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public Date getVipStartDate() {
        return vipStartDate;
    }

    public void setVipStartDate(Date vipStartDate) {
        this.vipStartDate = vipStartDate;
    }

    public Date getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(Date vipEndDate) {
        this.vipEndDate = vipEndDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(String isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserChat> getChatList() {
        return chatList;
    }

    public void setChatList(List<UserChat> chatList) {
        this.chatList = chatList;
    }

    public List<UserFollow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<UserFollow> followList) {
        this.followList = followList;
    }

    public List<SysCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<SysCoupon> couponList) {
        this.couponList = couponList;
    }
}
