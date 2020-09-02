package com.liu.animal.app.controller;

import com.liu.animal.app.entity.PetAdopt;
import com.liu.animal.app.entity.PetFoster;
import com.liu.animal.app.entity.UserTask;
import com.liu.animal.app.service.PetAdoptService;
import com.liu.animal.app.service.PetFosterService;
import com.liu.animal.app.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PetTaskController
 * @Description: 用户创建任务的类
 * @Author: Admin
 * @Date 2020/5/7 8:33
 **/
@Controller
@RequestMapping("pet-task")
public class PetTaskController {
    @Autowired
    private PetAdoptService petAdoptService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private UserTaskService userTaskService;

    /**
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Admin
     * @Description 该用户发布过的正在进行中的任务
     * @Date 14:46 2020/5/14
     **/
    @GetMapping("find-my-task")
    @ResponseBody
    public Map<String, Object> findMyTask(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<PetFoster> petFosterList = petFosterService.findByFromUserId(userId);
        List<PetAdopt> petAdoptList = petAdoptService.findByFromUserId(userId);
        map.put("petFosterList", petFosterList);
        map.put("petAdoptList", petAdoptList);
        return map;
    }

    /**
     * @param userId
     * @return
     * @Author Admin
     * @Description 该用户接收进行中的任务
     * @Date 14:56 2020/5/14
     **/
    @GetMapping("find-my-doing-task")
    @ResponseBody
    public Map<String, Object> findMyDoingTask(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<PetFoster> petFosterList = petFosterService.findByToUserId(userId);
        List<PetAdopt> petAdoptList = petAdoptService.findByToUserId(userId);
        map.put("petFosterList", petFosterList);
        map.put("petAdoptList", petAdoptList);
        return map;
    }

    /**
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Admin
     * @Description 该用户 结束了 评价和未评价的任务
     * @Date 15:19 2020/5/14
     **/
    @GetMapping("find-end-task")
    @ResponseBody
    public Map<String, Object> findEndTask(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<PetFoster> petFosterList = petFosterService.findEndByToUserId(userId);
        List<PetAdopt> petAdoptList = petAdoptService.findEndByToUserId(userId);
        map.put("petFosterList", petFosterList);
        map.put("petAdoptList", petAdoptList);
        return map;
    }
    /**
     * @Author Admin
     * @Description 查找想要的人的信息
     * @Date 17:03 2020/5/20
     * @param taskId
     * @param type
     * @return java.util.List<com.liu.animal.app.entity.UserTask>
     **/
    @GetMapping("find-want-user")
    @ResponseBody
    public List<UserTask> findWantUser(String taskId, String type) {
        return petFosterService.findWantUser(taskId, type);
    }
    /**
     * @Author Admin
     * @Description 修改用户与任务的信息
     * @Date 17:04 2020/5/20
     * @param userTask
     * @return java.lang.String
     **/
    @PostMapping("update-user-task")
    @ResponseBody
    public String updateUserTask(@RequestBody UserTask userTask) {
        int res = userTaskService.updateById(userTask);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 添加想要的人的
     * @Date 17:04 2020/5/20
     * @param userTask
     * @return java.lang.String
     **/
    @PostMapping("add-user-task")
    @ResponseBody
    public String addUserTask(@RequestBody UserTask userTask) {
        int res = userTaskService.addInfo(userTask);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除任务
     * @Date 17:04 2020/5/20
     * @param taskId
     * @return java.lang.String
     **/
    @GetMapping("delete-by-task-id")
    @ResponseBody
    public String deleteByTaskId(String taskId) {
        int res = userTaskService.deleteById(taskId);
        return res == 1 ? "success" : "fail";
    }


}