package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.SysCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysCouponMapper
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 20:24
 **/
@Mapper
public interface SysCouponMapper extends MapperPerent<SysCoupon>{
    List<SysCoupon> selectByUserId(String userId);
}