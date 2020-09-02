package com.liu.animal.app.service;

import com.liu.animal.app.entity.ArticlePhoto;

import java.util.List;

public interface ArticlePhotoService extends ServiceParent<ArticlePhoto>{
    List<ArticlePhoto> findByArticleInfoId(String id);
}
