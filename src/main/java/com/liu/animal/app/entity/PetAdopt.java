package com.liu.animal.app.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author Admin
 * @Description 用户领养
 * @Date 10:27 2020/5/2
 * @return
 **/

public class PetAdopt implements Serializable {

    private String id;
    private String petType;
    private Date createDate;
    private String createDateStr;
    private Date endDate;
    private String endDateStr;
    private String fromUserId;
    private String toUserId;
    private String description;
    private String evaluate;
    private BigDecimal price;
    private String city;
    private UserInfo userInfo;
    private PetInfo petInfo;
    private String petInfoId;
    private String fromUserName;
    private String toUserName;
    private Integer countOfPage;
    private String type;
    private String msg;
    private String isSuccess;
    private List<PetTaskPhoto> petTaskPhotoList;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public List<PetTaskPhoto> getPetTaskPhotoList() {
        return petTaskPhotoList;
    }

    public void setPetTaskPhotoList(List<PetTaskPhoto> petTaskPhotoList) {
        this.petTaskPhotoList = petTaskPhotoList;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCountOfPage() {
        return countOfPage;
    }

    public void setCountOfPage(Integer countOfPage) {
        this.countOfPage = countOfPage;
    }


    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getPetInfoId() {
        return petInfoId;
    }

    public void setPetInfoId(String petInfoId) {
        this.petInfoId = petInfoId;
    }

    public PetInfo getPetInfo() {
        return petInfo;
    }

    public void setPetInfo(PetInfo petInfo) {
        this.petInfo = petInfo;
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

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
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

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
