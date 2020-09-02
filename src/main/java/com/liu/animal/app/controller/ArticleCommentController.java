package com.liu.animal.app.controller;

import com.liu.animal.app.entity.ArticleComment;
import com.liu.animal.app.service.ArticleCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: CommentController
 * @Description:  文章评论控制器
 * @Author: Admin
 * @Date 2020/5/6 17:16
 **/
@Controller
@Slf4j
@RequestMapping("article-comment")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * @param articleInfoId
     * @return javax.xml.stream.events.Comment
     * @Author Admin
     * @Description 查询一篇文章的评论
     * @Date 2020/5/6
     **/
    @GetMapping("find-comment")
    @ResponseBody
    public ArticleComment findComment(String articleInfoId) {
        ArticleComment articleComment = articleCommentService.findById(articleInfoId);
        return articleComment;
    }
    /**
     * @Author Admin
     * @Description 添加文章的评论
     * @Date 16:42 2020/5/20
     * @param articleComment
     * @return java.lang.String
     **/
    @PostMapping("save-comment")
    @ResponseBody
    public String saveComment(@RequestBody ArticleComment articleComment) {
        int res = articleCommentService.addInfo(articleComment);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改对应的评论信息
     * @Date 16:43 2020/5/20
     * @param articleComment
     * @return java.lang.String
     **/
    @PostMapping("update-by-id")
    @ResponseBody
    public String updateById(@RequestBody ArticleComment articleComment) {
        int res = articleCommentService.updateById(articleComment);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 根据id删除评论
     * @Date 16:43 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-by-id")
    @ResponseBody
    public String deleteById(String id) {
        int res = articleCommentService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }

}