package com.liu.animal.app.entity;

import java.io.Serializable;

/**
 * @ClassName: PetPhoto
 * @Description: 宠物照片
 * @Author: Admin
 * @Date 2020/5/7 13:11
 **/

public class PetPhoto implements Serializable {
    private String id;
    private String photo;
    private Integer countOfPage;
    private String petInfoId;

    public String getPetInfoId() {
        return petInfoId;
    }

    public void setPetInfoId(String petInfoId) {
        this.petInfoId = petInfoId;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}