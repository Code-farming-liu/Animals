package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.ArticleComment;
import com.liu.animal.app.entity.ArticleInfo;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.mapper.ArticleCommentMapper;
import com.liu.animal.app.service.ArticleCommentService;
import com.liu.animal.app.service.ArticleInfoService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CommenServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 17:35
 **/
@Service
@Slf4j
@Transactional
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleInfoService articleInfoService;

    @Override
    public List<ArticleComment> findAll(Integer page, Integer num) {
        List<ArticleComment> articleComments = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(articleCommentMapper.count());
            } else {
                page = (page - 1) * num;
            }
            articleComments = articleCommentMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的findAll方法错误" + e.getMessage());
            return null;
        }
        for (ArticleComment articleComment : articleComments) {
            UserInfo userInfo = userService.findById(articleComment.getCommentUserId());
            articleComment.setCommentUserName(userInfo.getUsername());
            articleComment.setUserInfo(userInfo);
            ArticleInfo articleInfo = articleInfoService.findById(articleComment.getId());
            articleComment.setArticleInfo(articleInfo);
        }
        return articleComments;
    }

    @Override
    public int addInfo(ArticleComment articleComment) {
        articleComment.setCreateTime(new Date());
        try {
            return articleCommentMapper.insertInfo(articleComment);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的addInfo方法错误" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(ArticleComment articleComment) {
        try {
            return articleCommentMapper.updateById(articleComment);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的updateById方法错误" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return articleCommentMapper.deleteById(id);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的deleteById方法错误" + e.getMessage());
            return 0;
        }

    }

    @Override
    public ArticleComment findById(String id) {
        ArticleComment articleComment = null;
        try {
            articleComment = articleCommentMapper.selectById(id);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的findById方法错误" + e.getMessage());
            return null;
        }
        return articleComment;
    }

    @Override
    public String count() {
        try {
            return articleCommentMapper.count();
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的count方法错误" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<ArticleComment> findByArticleInfoId(String id) {
        List<ArticleComment> articleCommentList = null;
        try {
            articleCommentList = articleCommentMapper.selectByArticleInfoId(id);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的findByArticleInfoId方法错误" + e.getMessage());
        }
        for (ArticleComment articleComment : articleCommentList) {
            UserInfo userInfo = userService.findById(articleComment.getCommentUserId());
            articleComment.setUserInfo(userInfo);
        }
        Collections.sort(articleCommentList, new Comparator<ArticleComment>() {
            @Override
            public int compare(ArticleComment o1, ArticleComment o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return articleCommentList;
    }

    @Override
    public int deleteByArticleId(String id) {
        try {
            return articleCommentMapper.deleteByArticleId(id);
        } catch (Exception e) {
            log.error("ArticleCommentServiceImpl的deleteByArticleId方法错误" + e.getMessage());
        return 0;
        }
    }
}