package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.SysCoupon;
import com.liu.animal.app.entity.SysCouponUser;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.mapper.SysCouponMapper;
import com.liu.animal.app.service.SysCouponService;
import com.liu.animal.app.service.SysCouponUserService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: SysCouponServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 21:03
 **/
@Service
@Slf4j
@Transactional
public class SysCouponServiceImpl implements SysCouponService {
    @Autowired
    private SysCouponMapper sysCouponMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private SysCouponService sysCouponService;
    @Autowired
    private SysCouponUserService sysCouponUserService;

    @Override
    public List<SysCoupon> findAll(Integer page, Integer num) {
        List<SysCoupon> sysCoupons = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(sysCouponMapper.count());
            } else {
                page = (page - 1) * num;
            }
            sysCoupons = sysCouponMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return sysCoupons;
    }

    @Override
    public int addInfo(SysCoupon sysCoupon) {
        UserInfo userInfo = userService.findById(sysCoupon.getCreateUserId());
        if(userInfo.getIsPersonal().equals("1")){
            return 2;
        }
        try {
            return sysCouponMapper.insertInfo(sysCoupon);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(SysCoupon sysCoupon) {
        try {
            return sysCouponMapper.updateById(sysCoupon);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return sysCouponMapper.deleteById(id);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public SysCoupon findById(String id) {
        SysCoupon sysCoupon = null;
        try {
            sysCoupon = sysCouponMapper.selectById(id);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的findById方法出错" + e.getMessage());
        }
        UserInfo userInfo = userService.findById(sysCoupon.getCreateUserId());
        sysCoupon.setUserInfo(userInfo);
        sysCoupon.setCreateUserName(userInfo.getUsername());
        return sysCoupon;
    }

    @Override
    public String count() {
        try {
            return sysCouponMapper.count();
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<SysCoupon> findByUserId(String userId) {
        try {
            return sysCouponMapper.selectByUserId(userId);
        } catch (Exception e) {
            log.error("SysCouponServiceImpl的findByUserId出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public String getCoupon(SysCoupon sysCoupon,String userId) {
        sysCouponService.updateById(sysCoupon);
        SysCouponUser sysCouponUser = new SysCouponUser();
        sysCouponUser.setCouponId(sysCoupon.getId());
        sysCouponUser.setToUserId(userId);
        sysCouponUser.setCreateUserId(sysCoupon.getCreateUserId());
        sysCouponUserService.addInfo(sysCouponUser);
        return "success";
    }
}