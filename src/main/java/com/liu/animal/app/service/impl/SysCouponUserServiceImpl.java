package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.SysCoupon;
import com.liu.animal.app.entity.SysCouponUser;
import com.liu.animal.app.mapper.SysCouponUserMapper;
import com.liu.animal.app.service.SysCouponService;
import com.liu.animal.app.service.SysCouponUserService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: SysCouponUserServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/12 16:14
 **/
@Service
@Transactional
@Slf4j
public class SysCouponUserServiceImpl implements SysCouponUserService {
    @Autowired
    private SysCouponUserMapper sysCouponUserMapper;
    @Autowired
    private SysCouponService sysCouponService;
    @Autowired
    private UserService userService;

    @Override
    public List<SysCouponUser> findAll(Integer page, Integer num) {
        List<SysCouponUser> sysCouponUsers = null;
        try {
            sysCouponUsers = sysCouponUserMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的findAll方法出错" + e.getMessage());
        }
        return sysCouponUsers;
    }

    @Override
    public int addInfo(SysCouponUser sysCouponUser) {
        try {
            return sysCouponUserMapper.insertInfo(sysCouponUser);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(SysCouponUser sysCouponUser) {
        try {
            return sysCouponUserMapper.updateById(sysCouponUser);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return sysCouponUserMapper.deleteById(id);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public SysCouponUser findById(String id) {
        SysCouponUser sysCouponUser = null;
        try {
            sysCouponUser = sysCouponUserMapper.selectById(id);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return sysCouponUser;
    }

    @Override
    public String count() {
        try {
            return sysCouponUserMapper.count();
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的count方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<SysCoupon> findCouponByToUserId(String userId) {
        List<SysCoupon> sysCoupons = null;
        List<SysCouponUser> sysCouponUsers = null;
        try {
            sysCouponUsers = sysCouponUserMapper.selectCouponByToUserId(userId);
        } catch (Exception e) {
            log.error("SysCouponUserServiceImpl的findCouponByToUserId方法出错" + e.getMessage());
            return null;
        }
        for (SysCouponUser sysCouponUser : sysCouponUsers) {
            SysCoupon sysCoupon = sysCouponService.findById(sysCouponUser.getCouponId());
            sysCoupons.add(sysCoupon);
        }
        return sysCoupons;
    }
}