package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @Author Admin
 * @Description 文章评论表
 * @Date 10:16 2020/5/2
 * @return
 **/

public class ArticleComment implements Serializable {

    private String id;
    private String articleInfoId;
    private String content;
    private String commentUserId;
    private String commentUserName;
    private Integer praiseNum;
    private List<ArticleReply> replyList;
    private List<UserInfo> userInfoList;
    private UserInfo userInfo;
    private String replyCount;
    private ArticleInfo articleInfo;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(ArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleInfoId() {
        return articleInfoId;
    }

    public void setArticleInfoId(String articleInfoId) {
        this.articleInfoId = articleInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public List<ArticleReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ArticleReply> replyList) {
        this.replyList = replyList;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }
}
