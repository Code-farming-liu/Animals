package com.liu.animal.app.controller;

import com.liu.animal.app.entity.UserChat;
import com.liu.animal.app.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserChatController
 * @Description: 聊天
 * @Author: Admin
 * @Date 2020/5/9 13:35
 **/
@Controller
@RequestMapping("user-chat")
public class UserChatController {
    @Autowired
    private UserChatService userChatService;
    /**
     * @Author Admin
     * @Description 根据chatId查找对应的聊天信息
     * @Date 17:14 2020/5/20
     * @param chatId
     * @return java.util.List<com.liu.animal.app.entity.UserChat>
     **/
    @GetMapping(value = "find-user-chat")
    @ResponseBody
    public List<UserChat> findUserChatByChatId(String chatId) {
        return userChatService.findByChatId(chatId);
    }
    /**
     * @Author Admin
     * @Description 查找用户列表
     * @Date 17:14 2020/5/20
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-user-about-chat")
    @ResponseBody
    public Map<String,Object> findByUserId(String userId) {
        return userChatService.findByUserId(userId);
    }
    /**
     * @Author Admin
     * @Description 更改消息的 已读未读
     * @Date 17:15 2020/5/20
     * @param userChat
     * @return java.lang.String
     **/
    @PostMapping("update-read-by-id")
    @ResponseBody
    public String updateReadById(@RequestBody UserChat userChat) {
        int res = userChatService.updateByChatId(userChat);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除消息
     * @Date 17:15 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping ("delete-chat-by-id")
    @ResponseBody
    public String deleteChatById(String id) {
        int res = userChatService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
}