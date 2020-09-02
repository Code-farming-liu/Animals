package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetInfo;
import com.liu.animal.app.entity.PetPhoto;
import com.liu.animal.app.service.PetInfoService;
import com.liu.animal.app.service.PetPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PetInfoController
 * @Description: 宠物信息
 * @Author: Admin
 * @Date 2020/5/7 9:40
 **/
@Controller
@RequestMapping("pet-info")
public class PetInfoController {
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetPhotoService petPhotoService;
    /**
     * @Author Admin
     * @Description 根据id查找对应的 宠物信息
     * @Date 16:58 2020/5/20
     * @param petInfoId
     * @return com.liu.animal.app.entity.PetInfo
     **/
    @GetMapping("find-info-by-id")
    @ResponseBody
    public PetInfo findInfoById(String petInfoId) {
        return petInfoService.findById(petInfoId);
    }
    /**
     * @Author Admin
     * @Description 寻找所有的 宠物信息
     * @Date 16:58 2020/5/20
     * @param page
     * @param num
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-all-pet-info")
    @ResponseBody
    public Map<String, Object> findAllPetInfo(String page, String num) {
        Map<String, Object> map = new HashMap<>();
        List<PetInfo> petInfos = new ArrayList<>();
        if (page == null && num == null) {
            petInfos = petInfoService.findAll(null, null);
        } else {
            petInfos = petInfoService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        map.put("data", petInfos);
        map.put("count", petInfos.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 添加宠物的信息
     * @Date 16:59 2020/5/20
     * @param petInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @PostMapping("add-info")
    @ResponseBody
    public Map<String, Object> addInfo(@RequestBody PetInfo petInfo) {
        Map<String, Object> map = new HashMap<>();
        int res = petInfoService.addInfo(petInfo);
        PetInfo petInfo1 = petInfoService.findEndInfo();
        map.put("petInfoId",petInfo1.getId());
        map.put("state",res);
        return map;
    }
    /**
     * @Author Admin
     * @Description 修改宠物的信息
     * @Date 16:59 2020/5/20
     * @param petInfo
     * @return java.lang.String
     **/
    @PostMapping("update-info")
    @ResponseBody
    public String updateInfo(@RequestBody PetInfo petInfo) {
        int res = petInfoService.updateById(petInfo);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除宠物的信息
     * @Date 16:59 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @PostMapping("delete-info")
    @ResponseBody
    public String deleteInfo(String id) {
        int res = petInfoService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 补充 添加宠物信息
     * @Date 16:59 2020/5/20
     * @param petPhoto
     * @return java.lang.String
     **/
    @PostMapping("add-photo")
    @ResponseBody
    public String addPhoto(@RequestBody PetPhoto petPhoto) {
        int res = petPhotoService.addInfo(petPhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改宠物股照片的信息
     * @Date 17:00 2020/5/20
     * @param petPhoto
     * @return java.lang.String
     **/
    @PostMapping("update-photo")
    @ResponseBody
    public String updatePhoto(@RequestBody PetPhoto petPhoto) {
        int res = petPhotoService.updateById(petPhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除宠物的照片信息
     * @Date 17:00 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-photo")
    @ResponseBody
    public String deletePhoto(String id) {
        int res = petPhotoService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }

}