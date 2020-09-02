package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Admin
 * @Description 文章照片表
 * @Date 10:17 2020/5/2
 * @param
 * @return
 **/

public class ArticlePhoto implements Serializable {

  private String id;
  private String createUserId;
  private Date createDate;
  private String updateUserId;
  private Date updateDate;
  private String pictureUrl;
  private String articleInfoId;
  private List<UserInfo> userInfoList;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getArticleInfoId() {
    return articleInfoId;
  }

  public void setArticleInfoId(String articleInfoId) {
    this.articleInfoId = articleInfoId;
  }

  public List<UserInfo> getUserInfoList() {
    return userInfoList;
  }

  public void setUserInfoList(List<UserInfo> userInfoList) {
    this.userInfoList = userInfoList;
  }

}
