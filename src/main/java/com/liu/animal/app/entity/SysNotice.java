package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Admin
 * @Description 点赞通知表
 * @Date 10:32 2020/5/2
 * @return
 **/

public class SysNotice implements Serializable {

  private String id;
  private String fromUserId;
  private String fromUserName;
  private String toUserId;
  private String toUserName;
  private String articleInfoId;
  private String isRead;
  private String content;
  private String type;
  private String isSuccess;
  private List<UserInfo> userInfoList;
  private UserInfo userInfo;
  private List<ArticleInfo> articleInfoList;
//  private Integer countOfPage;
  private String commentId;
  private ArticleInfo articleInfo;
  private Date createTime;

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

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

  public String getCommentId() {
    return commentId;
  }

  public void setCommentId(String commentId) {
    this.commentId = commentId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(String isSuccess) {
    this.isSuccess = isSuccess;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getArticleInfoId() {
    return articleInfoId;
  }

  public void setArticleInfoId(String articleInfoId) {
    this.articleInfoId = articleInfoId;
  }

  public String getIsRead() {
    return isRead;
  }

  public void setIsRead(String isRead) {
    this.isRead = isRead;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<UserInfo> getUserInfoList() {
    return userInfoList;
  }

  public void setUserInfoList(List<UserInfo> userInfoList) {
    this.userInfoList = userInfoList;
  }

  public List<ArticleInfo> getArticleInfoList() {
    return articleInfoList;
  }

  public void setArticleInfoList(List<ArticleInfo> articleInfoList) {
    this.articleInfoList = articleInfoList;
  }
}
