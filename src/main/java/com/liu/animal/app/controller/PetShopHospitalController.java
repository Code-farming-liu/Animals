package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetShopHospital;
import com.liu.animal.app.service.PetShopHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PetShopHospitalController
 * @Description: 宠物医院 宠物店的基本信息
 * @Author: Admin
 * @Date 2020/5/12 20:15
 **/
@Controller
@RequestMapping("pet-shop-hospital")
public class PetShopHospitalController {
    @Autowired
    private PetShopHospitalService petShopHospitalService;
    /**
     * @Author Admin
     * @Description 查找宠物的基本信息
     * @Date 17:01 2020/5/20
     * @param page
     * @param num
     * @return java.util.List<com.liu.animal.app.entity.PetShopHospital>
     **/
    @GetMapping("find-all")
    @ResponseBody
    public List<PetShopHospital> findAll(String page, String num) {
        List<PetShopHospital> petShopHospitals = null;
        if (page == null && num == null) {
            petShopHospitals = petShopHospitalService.findAll(null, null);
        } else {
            petShopHospitals = petShopHospitalService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        return petShopHospitals;
    }
    /**
     * @Author Admin
     * @Description 根据id查找信息
     * @Date 17:01 2020/5/20
     * @param id
     * @return com.liu.animal.app.entity.PetShopHospital
     **/
    @GetMapping("find-shop-by-id")
    @ResponseBody
    public PetShopHospital findById(String id) {
        return petShopHospitalService.findById(id);
    }
    /**
     * @Author Admin
     * @Description 添加宠物店 宠物 医院的 基本信息
     * @Date 17:02 2020/5/20
     * @param petShopHospital
     * @return java.lang.String
     **/
    @PostMapping("add-info")
    @ResponseBody
    public String addInfo(@RequestBody PetShopHospital petShopHospital) {
        int res = petShopHospitalService.addInfo(petShopHospital);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改宠物店 宠物医院的基本信息
     * @Date 17:02 2020/5/20
     * @param petShopHospital
     * @return java.lang.String
     **/
    @PostMapping("update-info")
    @ResponseBody
    public String updateInfo(@RequestBody PetShopHospital petShopHospital) {
        int res = petShopHospitalService.updateById(petShopHospital);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除宠物店 宠物医院的 基本信息
     * @Date 17:02 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @PostMapping("delete-by-id")
    @ResponseBody
    public String deleteById(String id) {
        int res = petShopHospitalService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }


}