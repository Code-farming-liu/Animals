package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.UserChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserChatMapper extends MapperPerent<UserChat>{
    public List<UserChat> selectByChatId(String id);
    public int updateByChatId(UserChat userChat);

    List<UserChat> selectByUserId(String userId);

    List<UserChat> selectAboutChatByFromId(String fromUserId);

    UserChat selectLastByChatId(String chatId);

    List<UserChat> selectAboutChatByToId(String toUserId);
}
