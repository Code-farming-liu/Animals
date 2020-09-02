package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleCommentMapper extends MapperPerent<ArticleComment>{
    List<ArticleComment> selectByArticleInfoId(String id);

    int deleteByArticleId(String id);
}
