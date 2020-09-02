package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserFollow;

public interface UserFollowService extends ServiceParent<UserFollow>{
    int deleteByFollowUserId(UserFollow userFollow);
}
