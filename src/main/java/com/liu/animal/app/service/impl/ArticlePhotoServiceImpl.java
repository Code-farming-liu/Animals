package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.ArticlePhoto;
import com.liu.animal.app.mapper.ArticlePhotoMapper;
import com.liu.animal.app.service.ArticlePhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ArticlePhotoServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 10:34
 **/
@Service
@Transactional
@Slf4j
public class ArticlePhotoServiceImpl implements ArticlePhotoService {
    @Autowired
    private ArticlePhotoMapper articlePhotoMapper;

    @Override
    public List<ArticlePhoto> findAll(Integer page, Integer num) {
        List<ArticlePhoto> articlePhotoList = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(articlePhotoMapper.count());
            } else {
                page = (page - 1) * num;
            }
            articlePhotoList = articlePhotoMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的findAll方法出错" + e.getMessage());
        }
        return articlePhotoList;
    }

    @Override
    public int addInfo(ArticlePhoto articlePhoto) {
        try {
            return articlePhotoMapper.insertInfo(articlePhoto);
        } catch (Exception e) {
            log.error("文章照片保存失败" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(ArticlePhoto articlePhoto) {
        try {
            return articlePhotoMapper.updateById(articlePhoto);
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return articlePhotoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArticlePhoto findById(String id) {
        ArticlePhoto articlePhoto = null;
        try {
            articlePhoto = articlePhotoMapper.selectById(id);
            return articlePhoto;
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的findById方法出错" + e.getMessage());
        }
        return articlePhoto;
    }

    @Override
    public String count() {
        try {
            return articlePhotoMapper.count();
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<ArticlePhoto> findByArticleInfoId(String id) {
        List<ArticlePhoto> photoList = null;
        try {
            photoList = articlePhotoMapper.selectByArticlePhotoId(id);
        } catch (Exception e) {
            log.error("ArticlePhotoServiceImpl的findByArticleInfoId出错" + e.getMessage());
        }
        return photoList;
    }
}