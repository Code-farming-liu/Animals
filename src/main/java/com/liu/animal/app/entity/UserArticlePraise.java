package com.liu.animal.app.entity;

/**
 * @Author Admin
 * @Description 用户点赞
 * @Date 21:20 2020/5/23
 * @param null
 * @return
 **/
public class UserArticlePraise {
  private String id;
  private String articleInfoId;
  private String userInfoId;
  private Integer countOfPage;

  public Integer getCountOfPage() {
    return countOfPage;
  }

  public void setCountOfPage(Integer countOfPage) {
    this.countOfPage = countOfPage;
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

  public String getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(String userInfoId) {
    this.userInfoId = userInfoId;
  }
}
