package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserTask
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/12 8:22
 **/

public class UserTask implements Serializable {
    private String id;
    private String taskId;
    private String toUserId;
    private String toUserName;
    private String fromUserId;
    private String fromUserName;
    private String type;
    private String isSuccess;
    private List<UserInfo> userInfoList;
    private UserInfo userInfo;
    private List<PetInfo> petInfoList;
    private List<ArticleInfo> articleInfo;
    private PetFoster petFoster;
    private PetAdopt petAdopt;

    public PetFoster getPetFoster() {
        return petFoster;
    }

    public void setPetFoster(PetFoster petFoster) {
        this.petFoster = petFoster;
    }

    public PetAdopt getPetAdopt() {
        return petAdopt;
    }

    public void setPetAdopt(PetAdopt petAdopt) {
        this.petAdopt = petAdopt;
    }

    public List<PetInfo> getPetInfoList() {
        return petInfoList;
    }

    public void setPetInfoList(List<PetInfo> petInfoList) {
        this.petInfoList = petInfoList;
    }

    public List<ArticleInfo> getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(List<ArticleInfo> articleInfo) {
        this.articleInfo = articleInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}