package com.liu.animal.app.service;

import com.liu.animal.app.entity.SysCoupon;
import com.liu.animal.app.entity.SysCouponUser;

import java.util.List;

public interface SysCouponUserService extends ServiceParent<SysCouponUser>{
    List<SysCoupon> findCouponByToUserId(String userId);
}
