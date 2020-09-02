package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.ArticleInfo;
import com.liu.animal.app.entity.UserArticlePraise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ArticleInfoMapper extends MapperPerent<ArticleInfo>{
    List<UserArticlePraise> selectPraise(String userId);

    List<ArticleInfo> selectArticleByPetInfoId(String petInfoId);

    List<ArticleInfo> selectIsTopArticle(Integer counts);

    List<ArticleInfo> selectArticleByCreateUserId(String userId);

    ArticleInfo selectLastArticle();
}
