package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.UserArticlePraise;
import com.liu.animal.app.mapper.UserArticlePraiseMapper;
import com.liu.animal.app.service.UserArticlePraiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UserArticlePraiseServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/8 20:18
 **/
@Service
@Slf4j
@Transactional
public class UserArticlePraiseServiceImpl implements UserArticlePraiseService {
    @Autowired
    private UserArticlePraiseMapper userArticlePraiseMapper;

    @Override
    public List<UserArticlePraise> findAll(Integer page, Integer num) {
        List<UserArticlePraise> userArticlePraises = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userArticlePraiseMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userArticlePraises = userArticlePraiseMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return userArticlePraises;
    }

    @Override
    public int addInfo(UserArticlePraise userArticlePraise) {
        try {
            return userArticlePraiseMapper.insertInfo(userArticlePraise);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(UserArticlePraise userArticlePraise) {
        try {
            return userArticlePraiseMapper.updateById(userArticlePraise);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return userArticlePraiseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserArticlePraise findById(String id) {
        UserArticlePraise userArticlePraise = null;
        try {
            userArticlePraise = userArticlePraiseMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return userArticlePraise;
    }

    @Override
    public String count() {
        try {
            return userArticlePraiseMapper.count();
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的count方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteByArticleId(UserArticlePraise userArticlePraise) {
        try {
            return userArticlePraiseMapper.deleteByArticleId(userArticlePraise);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的deleteByArticleId方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deletePraiseNum(UserArticlePraise userArticlePraise) {
        try {
            return userArticlePraiseMapper.deletePraiseNum(userArticlePraise);
        } catch (Exception e) {
            log.error("UserArticlePraiseServiceImpl的deletePraiseNum方法出错" + e.getMessage());
            return 0;
        }
    }
}