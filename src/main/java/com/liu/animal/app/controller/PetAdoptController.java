package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetAdopt;
import com.liu.animal.app.service.PetAdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PetFoster
 * @Description: 领养控制器
 * @Author: Admin
 * @Date 2020/5/9 9:15
 **/
@Controller
@RequestMapping("pet-adopt")
public class PetAdoptController {
    @Autowired
    private PetAdoptService petAdoptService;

    /**
     * @param page
     * @param num
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Admin
     * @Description 寻找所有的领养信息
     * @Date 9:22 2020/5/9
     **/
    @GetMapping("find-all-adopt")
    @ResponseBody
    public Map<String, Object> findAllFoster(String page, String num) {
        Map<String, Object> maps = new HashMap<>();
        List<PetAdopt> petAdopts = new ArrayList<>();
        if (page == null && num == null) {
            petAdopts = petAdoptService.findAll(null, null);
        } else {
            petAdopts = petAdoptService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        maps.put("result", petAdopts);
        maps.put("领养count", petAdopts.size());
        return maps;
    }
    /**
     * @Author Admin
     * @Description 根据id寻找领养的信息
     * @Date 16:52 2020/5/20
     * @param id
     * @return com.liu.animal.app.entity.PetAdopt
     **/
    @GetMapping("find-adopt-by-id")
    @ResponseBody
    public PetAdopt findAdoptById(String id) {
        return petAdoptService.findById(id);
    }

    /**
     * @param petFoster
     * @return java.lang.String
     * @Author Admin
     * @Description 保存领养的基本信息
     * @Date 9:22 2020/5/9
     **/
    @PostMapping("add-adopt-info")
    @ResponseBody
    public String addFosterInfo(@RequestBody PetAdopt petFoster) {
        int res = petAdoptService.addInfo(petFoster);
        return res == 1 ? "success" : "fail";
    }

    /**
     * @param
     * @return
     * @Author Admin
     * @Description 修改寄养的基本信息
     * @Date 9:23 2020/5/9
     **/
    @PostMapping("update-adopt-info")
    @ResponseBody
    public String updateFosterInfo(@RequestBody PetAdopt petFoster) {
        int res = petAdoptService.updateById(petFoster);
        if (res == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param id
     * @return java.lang.String
     * @Author Admin
     * @Description 删除寄养的信息
     * @Date 9:30 2020/5/9
     **/
    @GetMapping("delete-adopt-info")
    @ResponseBody
    public String deleteFosterInfo(String id) {
        int res = petAdoptService.deleteById(id);
        if (res == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
    /**
     * @Author Admin
     * @Description 寻找用户对应的 领养信息
     * @Date 16:52 2020/5/20
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.PetAdopt>
     **/
    @GetMapping("find-adopt-foster")
    @ResponseBody
    public List<PetAdopt> findFromAdopt(String userId) {
        return petAdoptService.findByFromUserId(userId);
    }
    /**
     * @Author Admin
     * @Description 寻找用户将接受的领养信息
     * @Date 16:53 2020/5/20
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.PetAdopt>
     **/
    @GetMapping("find-to-adopt")
    @ResponseBody
    public List<PetAdopt> findToAdopt(String userId) {
        return petAdoptService.findByToUserId(userId);
    }
    /**
     * @Author Admin
     * @Description 修改领养的 状态
     * @Date 16:53 2020/5/20
     * @param petAdopt
     * @return java.lang.String
     **/
    @PostMapping("update-state-adopt")
    @ResponseBody
    public String updateStateAdopt(@RequestBody PetAdopt petAdopt) {
        return petAdoptService.updateStateFoster(petAdopt);
    }
}