package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.UserArticlePraise;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserArticlePraiseMapper extends MapperPerent<UserArticlePraise>{
    int deleteByArticleId(UserArticlePraise userArticlePraise);

    int deletePraiseNum(UserArticlePraise userArticlePraise);
}
