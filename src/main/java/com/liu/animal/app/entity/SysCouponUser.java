package com.liu.animal.app.entity;

import java.io.Serializable;

/**
 * @ClassName: SysCouponUser
 * @Description: 用户与优惠劵关系
 * @Author: Admin
 * @Date 2020/5/12 15:48
 **/

public class SysCouponUser implements Serializable {
    private String id;
    private String toUserId;
    private String toUserName;
    private String couponId;
    private String createUserId;
    private String createUserName;
    private UserInfo userInfo;
    private SysCoupon sysCoupon;

    public SysCoupon getSysCoupon() {
        return sysCoupon;
    }

    public void setSysCoupon(SysCoupon sysCoupon) {
        this.sysCoupon = sysCoupon;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}