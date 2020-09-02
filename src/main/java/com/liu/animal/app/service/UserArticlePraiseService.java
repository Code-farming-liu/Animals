package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserArticlePraise;

public interface UserArticlePraiseService extends ServiceParent<UserArticlePraise>{
    int deleteByArticleId(UserArticlePraise userArticlePraise);

    int deletePraiseNum(UserArticlePraise userArticlePraise);
}
