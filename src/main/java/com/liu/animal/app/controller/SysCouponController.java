package com.liu.animal.app.controller;

import com.liu.animal.app.entity.SysCoupon;
import com.liu.animal.app.service.SysCouponService;
import com.liu.animal.app.service.SysCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysCoupon
 * @Description: 优惠劵
 * @Author: Admin
 * @Date 2020/5/12 14:43
 **/
@Controller
@RequestMapping("sys-coupon")
public class SysCouponController {
    @Autowired
    private SysCouponService sysCouponService;
    @Autowired
    private SysCouponUserService sysCouponUserService;

    @GetMapping("find-all-coupon")
    @ResponseBody
    public Map<String, Object> findAllCoupon(String page, String num) {
        Map<String, Object> map = new HashMap<>();
        List<SysCoupon> sysCoupons;
        if (page == null && num == null) {
            sysCoupons = sysCouponService.findAll(null, null);
        } else {
            sysCoupons = sysCouponService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        map.put("result", sysCoupons);
        map.put("count", sysCoupons.size());
        return map;
    }

    @PostMapping("add-coupon-info")
    @ResponseBody
    public String addCouponInfo(@RequestBody SysCoupon sysCoupon) {
        int res = sysCouponService.addInfo(sysCoupon);
        if (res == 1) {
            return "success";
        } else if (res == 2) {
            return "个人用户无法发布优惠劵";
        } else {
            return "fail";
        }
    }

    @PostMapping("update-coupon-by-id")
    @ResponseBody
    public String updateCouponById(@RequestBody SysCoupon sysCoupon) {
        int res = sysCouponService.updateById(sysCoupon);
        return res == 1 ? "success" : "fail";
    }

    @PostMapping("delete-coupon-by-id")
    @ResponseBody
    public String deleteCouponById(String id) {
        int res = sysCouponService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }

    @GetMapping("find-coupon-by-id")
    @ResponseBody
    public SysCoupon findCouponById(String id) {
        return sysCouponService.findById(id);
    }

    /**
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.SysCoupon>
     * @Author Admin
     * @Description 发布人可以查看自己发布的优惠劵
     * @Date 15:40 2020/5/12
     **/
    @GetMapping("find-coupon-by-user-id")
    @ResponseBody
    public List<SysCoupon> findCouponByUserId(String userId) {
        return sysCouponService.findByUserId(userId);
    }

    /**
     * @param userId
     * @return java.util.List<com.liu.animal.app.entity.SysCoupon>
     * @Author Admin
     * @Description 该用户可以查找自己所拥有的优惠劵
     * @Date 17:14 2020/5/12
     **/
    @GetMapping("find-coupon-by-to-user-id")
    @ResponseBody
    public List<SysCoupon> findCouponByToUserId(String userId) {
        return sysCouponUserService.findCouponByToUserId(userId);
    }

    /**
     * @param sysCoupon
     * @return java.lang.String
     * @Author Admin
     * @Description 用户领取优惠劵
     * @Date 18:50 2020/5/12
     **/
    @PostMapping("get-coupon-by-id")
    @ResponseBody
    public String getCoupon(@RequestBody SysCoupon sysCoupon) {
        return sysCouponService.getCoupon(sysCoupon, sysCoupon.getUserId());
    }
}