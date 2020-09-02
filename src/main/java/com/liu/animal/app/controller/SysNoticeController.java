package com.liu.animal.app.controller;

import com.liu.animal.app.entity.SysNotice;
import com.liu.animal.app.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysNoticeController
 * @Description: 消息通知
 * @Author: Admin
 * @Date 2020/5/10 21:25
 **/
@Controller
@RequestMapping("sys-notice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;
    /**
     * @Author Admin
     * @Description 查找全部的消息
     * @Date 17:11 2020/5/20
     * @param page
     * @param num
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-all-notice")
    @ResponseBody
    public Map<String, Object> findAllNotice(String page, String num) {
        Map<String, Object> map = new HashMap<>();
        List<SysNotice> sysNoticeList = null;
        if (page == null && num == null) {
            sysNoticeList = sysNoticeService.findAll(null, null);
        } else {
            sysNoticeList = sysNoticeService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        map.put("data", sysNoticeList);
        map.put("count", sysNoticeList.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 查找该用户的有关的全部消息
     * @Date 17:11 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-notice-by-user-id")
    @ResponseBody
    public Map<String, Object> findNoticeByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<SysNotice> sysNoticeList = null;
        sysNoticeList = sysNoticeService.findByUserId(userId);
        map.put("data", sysNoticeList);
        map.put("count", sysNoticeList.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 根据id修改对赢得 通知信息
     * @Date 17:12 2020/5/20
     * @param sysNotice
     * @return java.lang.String
     **/
    @PostMapping("update-notice-by-id")
    @ResponseBody
    public String updateNoticeById(@RequestBody SysNotice sysNotice) {
        int res = sysNoticeService.updateById(sysNotice);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除该通知
     * @Date 17:12 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-notice")
    @ResponseBody
    public String deleteNoticeById(String id) {
        int res = sysNoticeService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
}