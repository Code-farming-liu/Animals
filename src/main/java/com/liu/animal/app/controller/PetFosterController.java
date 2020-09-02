package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetFoster;
import com.liu.animal.app.service.PetFosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PetFoster
 * @Description: 寄养
 * @Author: Admin
 * @Date 2020/5/9 9:15
 **/
@Controller
@RequestMapping("pet-foster")
public class PetFosterController {
    @Autowired
    private PetFosterService petFosterService;

    /**
     * @param page
     * @param num
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Admin
     * @Description 寻找所有的寄养信息
     * @Date 9:22 2020/5/9
     **/
    @GetMapping("find-all-foster")
    @ResponseBody
    public Map<String, Object> findAllFoster(String page, String num) {
        Map<String, Object> maps = new HashMap<>();
        List<PetFoster> petFosters = new ArrayList<>();
        if (page == null && num == null) {
            petFosters = petFosterService.findAll(null, null);
        } else {
            petFosters = petFosterService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        maps.put("result", petFosters);
        maps.put("寄养count", petFosters.size());
        return maps;
    }

    /**
     * @param petFoster
     * @return java.lang.String
     * @Author Admin
     * @Description 保存寄养的基本信息
     * @Date 9:22 2020/5/9
     **/
    @PostMapping("add-foster-info")
    @ResponseBody
    public String addFosterInfo(@RequestBody PetFoster petFoster) {
        int res = petFosterService.addInfo(petFoster);
        if (res == 1) {
            return "success";
        } else if (res == 0) {
            return "fail";
        } else {
            return "不是vip,无法进行重复寄养";
        }
    }

    /**
     * @param
     * @return
     * @Author Admin
     * @Description 修改寄养的基本信息
     * @Date 9:23 2020/5/9
     **/
    @PostMapping("update-foster-info")
    @ResponseBody
    public String updateFosterInfo(@RequestBody PetFoster petFoster) {
        int res = petFosterService.updateById(petFoster);
        return res == 1 ? "success" : "fail";
    }

    /**
     * @param id
     * @return java.lang.String
     * @Author Admin
     * @Description 删除寄养的信息
     * @Date 9:30 2020/5/9
     **/
    @GetMapping("delete-foster-info")
    @ResponseBody
    public String deleteFosterInfo(String id) {
        int res = petFosterService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 根据 任务的id 查询对应的寄养信息
     * @Date 16:55 2020/5/20
     * @param id
     * @return com.liu.animal.app.entity.PetFoster
     **/
    @GetMapping("find-foster-by-id")
    @ResponseBody
    public PetFoster findFosterById(String id) {
        return petFosterService.findById(id);
    }
    /**
     * @Author Admin
     * @Description 查找用户发布的 寄养信息
     * @Date 16:56 2020/5/20
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.PetFoster>
     **/
    @GetMapping("find-from-foster")
    @ResponseBody
    public List<PetFoster> findFromFoster(String userId) {
        return petFosterService.findByFromUserId(userId);
    }
    /**
     * @Author Admin
     * @Description 查找用户接受的寄养的信息
     * @Date 16:57 2020/5/20
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.PetFoster>
     **/
    @GetMapping("find-to-foster")
    @ResponseBody
    public List<PetFoster> findToFoster(String userId) {
        return petFosterService.findByToUserId(userId);
    }
    /**
     * @Author Admin
     * @Description 修复该对应的寄养信息的状态
     * @Date 16:57 2020/5/20
     * @param petFoster
     * @return java.lang.String
     **/
    @PostMapping("update-state-foster")
    @ResponseBody
    public String updateStateFoster(@RequestBody PetFoster petFoster) {
        return petFosterService.updateStateFoster(petFoster);
    }

}