package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.UserChat;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.mapper.UserChatMapper;
import com.liu.animal.app.service.UserChatService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: UserChatServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/3 14:08
 **/
@Service
@Transactional
@Slf4j
public class UserChatServiceImpl implements UserChatService {
    @Autowired
    private UserChatMapper userChatMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserChatService userChatService;

    @Override
    public List<UserChat> findByChatId(String id) {
        List<UserChat> userChats = null;
        try {
            userChats = userChatMapper.selectByChatId(id);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的findByChatId方法出错" + e.getMessage());
            return null;
        }
        for (UserChat userChat : userChats) {
            List<UserInfo> userInfoList = new ArrayList<>();
            if (userChat != null) {
                UserInfo userInfo = userService.findById(userChat.getFromUserId());
                UserInfo userInfo1 = userService.findById(userChat.getToUserId());
                userChat.setUserInfo(userInfo1);
                userInfoList.add(userInfo1);
                userInfoList.add(userInfo);
            }
            userChat.setUserInfoList(userInfoList);
        }
        return userChats;
    }

    @Override
    public int updateByChatId(UserChat userChat) {
        try {
            userChatMapper.updateByChatId(userChat);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的updateByChatId方法出错" + e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public Map<String, Object> findByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<UserChat> listList = new ArrayList<>();
        List<UserChat> userChatList = null;
        try {
            userChatList = userChatMapper.selectByUserId(userId);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的findByUserId方法出错" + e.getMessage());
            return null;
        }
        int total = 0;
        Set<String> set = new HashSet<>();
        for (UserChat userChat : userChatList) {
            set.add(userChat.getChatId());
        }

        for (String chatId : set) {
            int count = 0;
            List<UserChat> userChats = userChatMapper.selectByChatId(chatId);
            for (UserChat userChat : userChats) {
                if (userChat.getRead().equals("0")) {
                    count++;
                }
            }
            UserChat userChat1 = userChatService.findLastByChatId(chatId);
            userChat1.setReadCount(count + "");
            listList.add(userChat1);
            total += count;
        }
        Collections.sort(listList, new Comparator<UserChat>() {
            @Override
            public int compare(UserChat o1, UserChat o2) {
                return (int) o2.getCreateTime().getTime() - (int) o1.getCreateTime().getTime();
            }
        });
        map.put("data", listList);
        map.put("readTotal", total);
        return map;
    }

    @Override
    public UserChat findLastByChatId(String chatId) {
        UserChat userChat = null;
        List<UserInfo> userInfos = new ArrayList<>();
        try {
            userChat = userChatMapper.selectLastByChatId(chatId);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的findLastByChatId方法出错" + e.getMessage());
            return null;
        }
        UserInfo userInfo1 = userService.findById(userChat.getFromUserId());
        UserInfo userInfo = userService.findById(userChat.getToUserId());
        userChat.setUserInfo(userInfo1);
        userInfos.add(userInfo);
        userInfos.add(userInfo1);
        userChat.setUserInfoList(userInfos);
        userChat.setLastChatContent(userChat.getContent());
        return userChat;
    }


    @Override
    public List<UserChat> findAll(Integer page, Integer num) {
        List<UserChat> userChats = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userChatMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userChats = userChatMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return userChats;
    }

    @Override
    public int addInfo(UserChat userChat) {
        try {
            return userChatMapper.insertInfo(userChat);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }

    }

    @Override
    public int updateById(UserChat userChat) {
        try {
            return userChatMapper.updateById(userChat);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userChatMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserChat findById(String id) {
        UserChat userChat = null;
        try {
            userChat = userChatMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserChatServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return userChat;
    }

    @Override
    public String count() {
        try {
            return userChatMapper.count();
        } catch (Exception e) {
            log.error("UserChatServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}