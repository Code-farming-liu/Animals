package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.Date;
/**
 * @Author Admin
 * @Description //每天定时统计发表的博客数量
 * @Date 10:31 2020/5/2
 * @return
 **/

public class SysCountOfDay implements Serializable {

  private String id;
  private String articleInfoId;
  private String userId;
  private String count;
  private Date createTime;
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
