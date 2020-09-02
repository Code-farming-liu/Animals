package com.liu.animal.app.entity;

import java.io.Serializable;

/**
 * @Author Admin
 * @Description 宠物习性表
 * @Date 10:22 2020/5/2
 * @param
 * @return
 **/

public class PetHabit implements Serializable {

    private String id;
    private String petInfoId;
    private String petSelectSkill;
    private String petFeedPoints;
    private String petTrainSkill;
    private String petCommonIllness;
//    private List<UserInfo> userInfoList;
//    private Integer countOfPage;

//    public Integer getCountOfPage() {
//        return countOfPage;
//    }
//
//    public void setCountOfPage(Integer countOfPage) {
//        this.countOfPage = countOfPage;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetInfoId() {
        return petInfoId;
    }

    public void setPetInfoId(String petInfoId) {
        this.petInfoId = petInfoId;
    }

    public String getPetSelectSkill() {
        return petSelectSkill;
    }

    public void setPetSelectSkill(String petSelectSkill) {
        this.petSelectSkill = petSelectSkill;
    }

    public String getPetFeedPoints() {
        return petFeedPoints;
    }

    public void setPetFeedPoints(String petFeedPoints) {
        this.petFeedPoints = petFeedPoints;
    }

    public String getPetTrainSkill() {
        return petTrainSkill;
    }

    public void setPetTrainSkill(String petTrainSkill) {
        this.petTrainSkill = petTrainSkill;
    }

    public String getPetCommonIllness() {
        return petCommonIllness;
    }

    public void setPetCommonIllness(String petCommonIllness) {
        this.petCommonIllness = petCommonIllness;
    }

}
