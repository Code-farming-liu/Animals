package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.ArticlePhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticlePhotoMapper extends MapperPerent<ArticlePhoto>{
    List<ArticlePhoto> selectByArticlePhotoId(String id);
}
