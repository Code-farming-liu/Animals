package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.ArticleReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleReplyMapper extends MapperPerent<ArticleReply>{
    List<ArticleReply> selectByCommentId(String commentId);

    int deleteByCommentId(String id);
}
