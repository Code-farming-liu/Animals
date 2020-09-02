package com.liu.animal.app.entity;


import java.io.Serializable;

/**
 * @Author Admin
 * @Description 宠物任务照片
 * @Date 10:27 2020/5/2
 * @return
 **/

public class PetTaskPhoto implements Serializable {
    private String id;
    private String petTaskId;
    private String photo;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetTaskId() {
        return petTaskId;
    }

    public void setPetTaskId(String petTaskId) {
        this.petTaskId = petTaskId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
