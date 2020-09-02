package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.ArticleReply;
import com.liu.animal.app.mapper.ArticleReplyMapper;
import com.liu.animal.app.service.ArticleReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArticleReplyServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 20:48
 **/
@Service
@Slf4j
@Transactional
public class ArticleReplyServiceImpl implements ArticleReplyService {
    @Autowired
    private ArticleReplyMapper articleReplyMapper;

    @Override
    public List<ArticleReply> findAll(Integer page, Integer num) {
        List<ArticleReply> replyList = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(articleReplyMapper.count());
            } else {
                page = (page - 1) * num;
            }
            replyList = articleReplyMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的findAll出错" + e.getMessage());
            return null;
        }
        return replyList;
    }

    @Override
    public int addInfo(ArticleReply articleReply) {
        articleReply.setCreateTime(new Date());
        try {
            articleReplyMapper.insertInfo(articleReply);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的addInfo出错" + e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int updateById(ArticleReply articleReply) {
        try {
            return articleReplyMapper.updateById(articleReply);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return articleReplyMapper.deleteById(id);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArticleReply findById(String id) {
        ArticleReply articleReply = null;
        try {
            articleReply = articleReplyMapper.selectById(id);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的deleteById出错" + e.getMessage());
            return null;
        }
        return articleReply;
    }

    @Override
    public List<ArticleReply> findByCommentId(String commentId) {
        List<ArticleReply> replyList = null;
        try {

            replyList = articleReplyMapper.selectByCommentId(commentId);
        } catch (Exception e) {
            log.error("根据评论的Id查询回复失败" + e.getMessage());
            return null;
        }
        Collections.sort(replyList, new Comparator<ArticleReply>() {
            @Override
            public int compare(ArticleReply o1, ArticleReply o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return replyList;
    }

    @Override
    public int deleteByCommentId(String id) {
        try {
            return articleReplyMapper.deleteByCommentId(id);
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的deleteByCommentId出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public String count() {
        try {
            return articleReplyMapper.count();
        } catch (Exception e) {
            log.error("ArticleReplyServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}