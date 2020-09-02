package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.SysCouponUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCouponUserMapper extends MapperPerent<SysCouponUser> {
    List<SysCouponUser> selectCouponByToUserId(String userId);
}
