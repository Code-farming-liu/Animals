package com.liu.animal.app.service;

import com.liu.animal.app.entity.ArticleReply;

import java.util.List;

public interface ArticleReplyService extends ServiceParent<ArticleReply>{
    List<ArticleReply> findByCommentId(String commentId);

    int deleteByCommentId(String id);
}
