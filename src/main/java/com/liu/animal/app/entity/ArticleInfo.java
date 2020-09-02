package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @Author Admin
 * @Description 文章信息表
 * @Date 10:17 2020/5/2
 * @return
 **/

public class ArticleInfo implements Serializable {

    private String id;
    private String createUserId;
    private String createUserName;
    private Date createTime;
    private String content;
    private String updateUserId;
    private Date updateDate;
    private String title;
    private String isDelete;
    private String type;
    private String isTop;
    private String isOriginal;
    private String isPrivate;
    private Integer praiseNum;
    private Integer forwardNum;
    private List<ArticlePhoto> photoList;
    private List<ArticleComment> commentList;
    private UserInfo userInfo;
    private String isPraise;
    private String isFollow;
    private String commentCount;
    private String petInfoId;
    private PetInfo petInfo;

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

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(String isPraise) {
        this.isPraise = isPraise;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }



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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsDelete() {
//    if(StringUtils.isNotEmpty(isDelete)) {
//      if (isDelete.equals("1")) {
//        isDelete = "是";
//      } else if (isDelete.equals("0")) {
//        isDelete = "否";
//      }
//    }
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsTop() {
//    if(StringUtils.isNotEmpty(isTop)) {
//      if (isTop.equals("1")) {
//        isTop = "上推荐";
//      } else if (isTop.equals("0")) {
//        isTop = "不上推荐";
//      }
//    }
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public List<ArticlePhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<ArticlePhoto> photoList) {
        this.photoList = photoList;
    }

    public List<ArticleComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ArticleComment> commentList) {
        this.commentList = commentList;
    }

}
