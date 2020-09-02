package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowMapper extends MapperPerent<UserFollow>{
    int deleteByFollowUserId(UserFollow userFollow);
}
