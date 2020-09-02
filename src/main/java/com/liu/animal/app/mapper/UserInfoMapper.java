package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.UserFollow;
import com.liu.animal.app.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper extends MapperPerent<UserInfo>{
    UserInfo selectByUsername(String username);

    UserInfo selectByPhone(String phone);

    UserInfo selectByEmail(String email);

    List<UserFollow> selectFollow(String userId);

    UserInfo selectEndInfo();

    List<UserFollow> selectFan(String id);
}
