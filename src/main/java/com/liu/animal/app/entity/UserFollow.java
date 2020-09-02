package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.List;

/**
 * @Author Admin
 * @Description 用户的关注粉丝表
 * @Date 10:40 2020/5/2
 * @return
 **/

public class UserFollow implements Serializable {

  private String id;
  private String followUserId;
  private String fanUserId;
  private List<UserInfo> userInfoList;
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

  public String getFollowUserId() {
    return followUserId;
  }

  public void setFollowUserId(String followUserId) {
    this.followUserId = followUserId;
  }

  public String getFanUserId() {
    return fanUserId;
  }

  public void setFanUserId(String fanUserId) {
    this.fanUserId = fanUserId;
  }

  public List<UserInfo> getUserInfoList() {
    return userInfoList;
  }

  public void setUserInfoList(List<UserInfo> userInfoList) {
    this.userInfoList = userInfoList;
  }
}
