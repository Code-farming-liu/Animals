package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserChat;

import java.util.List;
import java.util.Map;

public interface UserChatService extends ServiceParent<UserChat> {
    public List<UserChat> findByChatId(String id);
    public int updateByChatId(UserChat userChat);

    Map<String, Object> findByUserId(String userId);

    UserChat findLastByChatId(String chatId);
}
