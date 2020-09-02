package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetTaskPhoto;
import com.liu.animal.app.service.PetTaskPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PetTaskPhotoController
 * @Description: 任务照片
 * @Author: Admin
 * @Date 2020/5/17 15:11
 **/
@Controller
@RequestMapping("pet-photo")
public class PetTaskPhotoController {
    @Autowired
    private PetTaskPhotoService petTaskPhotoService;
    /**
     * @Author Admin
     * @Description 根据任务的 id 和 type类型来查找对应的信息
     * @Date 17:05 2020/5/20
     * @param petTaskId
     * @param type
     * @return java.util.List<com.liu.animal.app.entity.PetTaskPhoto>
     **/
    @GetMapping("find-photo")
    @ResponseBody
    public List<PetTaskPhoto> findPhoto(String petTaskId, String type) {
        return petTaskPhotoService.findPhoto(petTaskId, type);
    }
    /**
     * @Author Admin
     * @Description 添加任务的照片
     * @Date 17:05 2020/5/20
     * @param petTaskPhoto
     * @return java.lang.String
     **/
    @PostMapping("add-photo")
    @ResponseBody
    public String addPhoto(@RequestBody PetTaskPhoto petTaskPhoto) {
        int res = petTaskPhotoService.addPhoto(petTaskPhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改任务照片
     * @Date 17:06 2020/5/20
     * @param petTaskPhoto
     * @return java.lang.String
     **/
    @PostMapping("update-photo")
    @ResponseBody
    public String updateById(@RequestBody PetTaskPhoto petTaskPhoto) {
        int res = petTaskPhotoService.updateById(petTaskPhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除对应的任务的照片
     * @Date 17:06 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-photo")
    @ResponseBody
    public String deleteById(String id) {
       int res = petTaskPhotoService.deleteById(id);
       return res == 1 ? "success" : "fail";
    }
}