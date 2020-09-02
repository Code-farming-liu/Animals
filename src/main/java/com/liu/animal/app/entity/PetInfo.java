package com.liu.animal.app.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @Author Admin
 * @Description 宠物信息表
 * @Date 10:23 2020/5/2
 * @return
 **/

public class PetInfo implements Serializable {

    private String id;
    private String type;
    private String typeStr;
    private String petPhotoId;
    private String kind;
    private String name;
    private String description;
    private String age;
    private String sex;
    private List<PetShopHospital> shopHospitalList;
    private List<PetHabit> habitList;
    private List<PetPhoto> petPhotoList;
    private String userInfoId;
    private String petInfoId;

    public String getPetInfoId() {
        return petInfoId;
    }

    public void setPetInfoId(String petInfoId) {
        this.petInfoId = petInfoId;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTypeStr() {
        if (StringUtils.isNotEmpty(type)) {
            if (type.equals("1")) {
                typeStr = "宠物领养";
            } else if (type.equals("2")) {
                typeStr = "宠物寄养";
            }
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public List<PetPhoto> getPetPhotoList() {
        return petPhotoList;
    }

    public void setPetPhotoList(List<PetPhoto> petPhotoList) {
        this.petPhotoList = petPhotoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPetPhotoId() {
        return petPhotoId;
    }

    public void setPetPhotoId(String petPhotoId) {
        this.petPhotoId = petPhotoId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PetShopHospital> getShopHospitalList() {
        return shopHospitalList;
    }

    public void setShopHospitalList(List<PetShopHospital> shopHospitalList) {
        this.shopHospitalList = shopHospitalList;
    }

    public List<PetHabit> getHabitList() {
        return habitList;
    }

    public void setHabitList(List<PetHabit> habitList) {
        this.habitList = habitList;
    }

}
