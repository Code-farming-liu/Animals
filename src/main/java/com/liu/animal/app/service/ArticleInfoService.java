package com.liu.animal.app.service;

import com.liu.animal.app.entity.ArticleInfo;
import com.liu.animal.app.entity.SysNotice;
import com.liu.animal.app.entity.UserArticlePraise;

import java.util.List;

public interface ArticleInfoService extends ServiceParent<ArticleInfo>{
//    List<ArticleInfo> findPraiseAndFollow(String userId);
    List<Integer> findPraise(String userId);
    int praiseNum(SysNotice sysNotice, ArticleInfo articleInfo, UserArticlePraise userArticlePraise);
    String addArticleInfo(ArticleInfo articleInfo);

    List<ArticleInfo> findArticleByPetInfoId(String petInfoId);

    List<ArticleInfo> findIsTopArticle(Integer count);

    List<ArticleInfo> findArticleByCreateUserId(String userId);

    List<List<ArticleInfo>> findFollowInfo(String userId);

    List<ArticleInfo> findHotInfo();

//    int removePraiseNum(SysNotice sysNotice, ArticleInfo articleInfo, String key, UserArticlePraise userArticlePraise);
}
