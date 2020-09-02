package com.liu.animal.app.controller;

import com.liu.animal.app.entity.ArticleInfo;
import com.liu.animal.app.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArticleInfoController
 * @Description: 文章信息控制器
 * @Author: Admin
 * @Date 2020/5/8 15:13
 **/
@Controller
@RequestMapping("article-info")
public class ArticleInfoController {
    @Autowired
    private ArticleInfoService articleInfoService;
    /**
     * @Author Admin
     * @Description 寻找全部文章
     * @Date 16:44 2020/5/20
     * @param page 页数
     * @param num  每页的条数
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-all")
    @ResponseBody
    public Map<String, Object> findAll(String page, String num) {
        Map<String, Object> map = new HashMap<>();
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        if (page == null && num == null) {
            articleInfoList = articleInfoService.findAll(null, null);
        } else {
            articleInfoList = articleInfoService.findAll(Integer.parseInt(page), Integer.parseInt(num));
        }
        map.put("data", articleInfoList);
        map.put("count", articleInfoList.size());
        return map;
    }
    /**
     * @Author Admin
     * @Description 根据id查找对应的文章信息
     * @Date 16:45 2020/5/20
     * @param id
     * @return com.liu.animal.app.entity.ArticleInfo
     **/
    @GetMapping("find-article-info-id")
    @ResponseBody
    public ArticleInfo findArticleInfo(String id) {
        return articleInfoService.findById(id);
    }
    /**
     * @Author Admin
     * @Description 寻找关注人的动态
     * @Date 16:46 2020/5/20
     * @param userId
     * @return java.util.List<java.util.List < com.liu.animal.app.entity.ArticleInfo>>
     **/
    @GetMapping("find-follow-info")
    @ResponseBody
    public List<List<ArticleInfo>> findFollowInfo(String userId) {
        return articleInfoService.findFollowInfo(userId);
    }
    /**
     * @Author Admin
     * @Description 寻找热门的动态
     * @Date 16:46 2020/5/20
     * @param
     * @return java.util.List<com.liu.animal.app.entity.ArticleInfo>
     **/
    @GetMapping("find-hot-info")
    @ResponseBody
    public List<ArticleInfo> findHotInfo() {
        return articleInfoService.findHotInfo();
    }
    /**
     * @Author Admin
     * @Description 寻找点赞文章的id
     * @Date 16:47 2020/5/20
     * @param userId
     * @return java.util.List<java.lang.Integer>
     **/
    @RequestMapping("find-praise")
    @ResponseBody
    public List<Integer> findPraise(String userId) {
        return articleInfoService.findPraise(userId);
    }
    /**
     * @Author Admin
     * @Description 添加对应的文章信息
     * @Date 16:47 2020/5/20
     * @param articleInfo
     * @return java.lang.String
     **/
    @PostMapping("add-article-info")
    @ResponseBody
    public String addArticleInfo(@RequestBody ArticleInfo articleInfo) {
        return articleInfoService.addArticleInfo(articleInfo);
    }
    /**
     * @Author Admin
     * @Description 修改对应的文章信息
     * @Date 16:47 2020/5/20
     * @param articleInfo
     * @return java.lang.String
     **/
    @PostMapping("update-article-by-id")
    @ResponseBody
    public String updateArticleInfo(@RequestBody ArticleInfo articleInfo) {
        int res = articleInfoService.updateById(articleInfo);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除文章的id
     * @Date 16:47 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @PostMapping("delete-article-by-id")
    @ResponseBody
    public String deleteArticleById(String id) {
        int res = articleInfoService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 根据宠物的id寻找对应的动态
     * @Date 16:48 2020/5/20
     * @param petInfoId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("find-article-by-pet-info-id")
    @ResponseBody
    public Map<String, Object> findArticleByPetInfoId(String petInfoId) {
        Map<String, Object> map = new HashMap<>();
        List<ArticleInfo> articleInfoList = articleInfoService.findArticleByPetInfoId(petInfoId);
        map.put("data", articleInfoList);
        map.put("count", articleInfoList.size());
        return map;
    }
}