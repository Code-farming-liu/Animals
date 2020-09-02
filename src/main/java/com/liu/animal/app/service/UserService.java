package com.liu.animal.app.service;

import com.liu.animal.app.entity.ArticleInfo;
import com.liu.animal.app.entity.PetInfo;
import com.liu.animal.app.entity.UserInfo;

import java.util.List;

public interface UserService extends ServiceParent<UserInfo> {
    UserInfo findByUsername(String username);

    UserInfo findByPhone(String phone);

    UserInfo findByEmail(String email);

    String loginByUsername(String username, String password);

    public String phoneCode(String phone);

    public String emailCode(String email);

    public String register(UserInfo userInfo);

    List<UserInfo> findFollow(String userId);

    UserInfo findEndInfo();

    List<UserInfo> findFan(String id);

    public String checkEmail(String email);

    List<PetInfo> findPet(String userId);

    List<ArticleInfo> findArticleByUserId(String userId);

    public String checkPhone(String phone);

    String loginByPhone(String username, String password);

    String loginByEmail(String username, String password);

}
