package com.liu.animal.app.service;

import com.liu.animal.app.entity.ArticleComment;

import java.util.List;


public interface ArticleCommentService extends ServiceParent<ArticleComment>{
    List<ArticleComment> findByArticleInfoId(String id);

    int deleteByArticleId(String id);
}

