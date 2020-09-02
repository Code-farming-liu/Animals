package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.UserFollow;
import com.liu.animal.app.mapper.UserFollowMapper;
import com.liu.animal.app.service.UserFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UserFollowServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/8 21:30
 **/
@Transactional
@Slf4j
@Service
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public List<UserFollow> findAll(Integer page, Integer num) {
        List<UserFollow> userFollows = null;
        if (page == null && num == null) {
            page = 0;
            num = Integer.parseInt(userFollowMapper.count());
        } else {
            page = (page - 1) * num;
        }
        try {
            userFollows = userFollowMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return userFollows;
    }

    @Override
    public int addInfo(UserFollow userFollow) {
        try {
            return userFollowMapper.insertInfo(userFollow);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(UserFollow userFollow) {
        try {
            return userFollowMapper.updateById(userFollow);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userFollowMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserFollow findById(String id) {
        UserFollow userFollow = null;
        try {
            userFollow = userFollowMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的findById方法出错" + e.getMessage());
        }
        return userFollow;
    }

    @Override
    public String count() {
        try {
            return userFollowMapper.count();
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的count方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteByFollowUserId(UserFollow userFollow) {
        try {
            return userFollowMapper.deleteByFollowUserId(userFollow);
        } catch (Exception e) {
            log.error("UserFollowServiceImpl的deleteByFollowUserId方法出错" + e.getMessage());
            return 0;
        }
    }
}