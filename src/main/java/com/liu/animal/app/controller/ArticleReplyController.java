package com.liu.animal.app.controller;

import com.liu.animal.app.entity.ArticleReply;
import com.liu.animal.app.service.ArticleReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ArticleReplyController
 * @Description: 回复
 * @Author: Admin
 * @Date 2020/5/6 18:52
 **/
@Controller
@RequestMapping("article-reply")
public class ArticleReplyController {
    @Autowired
    private ArticleReplyService articleReplyService;
    /**
     * @Author Admin
     * @Description 根据评论的id查找对应的 回复信息
     * @Date 16:50 2020/5/20
     * @param commentId
     * @return java.util.List<com.liu.animal.app.entity.ArticleReply>
     **/
    @GetMapping("find-reply-by-comment-id")
    @ResponseBody
    public List<ArticleReply> findReplyByCommentId(String commentId) {
        return articleReplyService.findByCommentId(commentId);
    }
    /**
     * @Author Admin
     * @Description 添加对应的 回复信息
     * @Date 16:50 2020/5/20
     * @param articleReply
     * @return java.lang.String
     **/
    @PostMapping("add-reply")
    @ResponseBody
    public String addReply(@RequestBody ArticleReply articleReply) {
        int res = articleReplyService.addInfo(articleReply);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改对应的 回复信息
     * @Date 16:50 2020/5/20
     * @param articleReply
     * @return java.lang.String
     **/
    @PostMapping("update-reply-by-id")
    @ResponseBody
    public String updateReplyById(@RequestBody ArticleReply articleReply) {
        int res = articleReplyService.updateById(articleReply);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除对应的回复
     * @Date 16:51 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-reply")
    @ResponseBody
    public String deleteById(String id) {
        int res = articleReplyService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
}