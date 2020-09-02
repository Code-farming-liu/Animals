package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @Author Admin
 * @Description 优惠劵
 * @Date 10:32 2020/5/2
 * @return
 **/

public class SysCoupon implements Serializable {

    private String id;
    private String description;
    private Date createDate;
    private Date endDate;
    private String type;
    private String couponPetId;
    private String createUserId;
    private String createUserName;
    private String petShopHospitalId;
    private String num;
    private String userId;
    private List<PetInfo> petInfoList;
    private List<SysCouponUser> sysCouponUserList;
    private UserInfo userInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<SysCouponUser> getSysCouponUserList() {
        return sysCouponUserList;
    }

    public void setSysCouponUserList(List<SysCouponUser> sysCouponUserList) {
        this.sysCouponUserList = sysCouponUserList;
    }




    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouponPetId() {
        return couponPetId;
    }

    public void setCouponPetId(String couponPetId) {
        this.couponPetId = couponPetId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getPetShopHospitalId() {
        return petShopHospitalId;
    }

    public void setPetShopHospitalId(String petShopHospitalId) {
        this.petShopHospitalId = petShopHospitalId;
    }

    public List<PetInfo> getPetInfoList() {
        return petInfoList;
    }

    public void setPetInfoList(List<PetInfo> petInfoList) {
        this.petInfoList = petInfoList;
    }


}
