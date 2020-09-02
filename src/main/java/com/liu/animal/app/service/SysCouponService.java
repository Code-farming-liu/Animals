package com.liu.animal.app.service;

import com.liu.animal.app.entity.SysCoupon;

import java.util.List;

public interface SysCouponService extends ServiceParent<SysCoupon>{
    List<SysCoupon> findByUserId(String userId);

    String getCoupon(SysCoupon sysCoupon,String userId);
}
