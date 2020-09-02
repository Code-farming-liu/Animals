package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @Author Admin
 * @Description 回复表
 * @Date 10:21 2020/5/2
 * @param
 * @return
 **/

public class ArticleReply implements Serializable {

    private String id;
    private String commentId;
    private String content;
    private String fromUserId;
    private String fromUserStr;
    private String toUserId;
    private String toUserStr;
    private Integer praiseNum;
    private UserInfo userInfo;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getFromUserStr() {
        return fromUserStr;
    }

    public void setFromUserStr(String fromUserStr) {
        this.fromUserStr = fromUserStr;
    }

    public String getToUserStr() {
        return toUserStr;
    }

    public void setToUserStr(String toUserStr) {
        this.toUserStr = toUserStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

}
