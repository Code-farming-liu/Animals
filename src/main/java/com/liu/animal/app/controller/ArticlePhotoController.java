package com.liu.animal.app.controller;

import com.liu.animal.app.entity.ArticlePhoto;
import com.liu.animal.app.service.ArticlePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ArticlePhotoController
 * @Description: 文章照片的控制器
 * @Author: Admin
 * @Date 2020/5/15 17:57
 **/
@Controller
@RequestMapping("article-photo")
public class ArticlePhotoController {
    @Autowired
    private ArticlePhotoService articlePhotoService;
    /**
     * @Author Admin
     * @Description 补充 添加 照片
     * @Date 16:48 2020/5/20
     * @param articlePhoto
     * @return java.lang.String
     **/
    @PostMapping("add-photo")
    @ResponseBody
    public String addPhoto(@RequestBody ArticlePhoto articlePhoto) {
        int res = articlePhotoService.addInfo(articlePhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 修改对应的照片信息
     * @Date 16:49 2020/5/20
     * @param articlePhoto
     * @return java.lang.String
     **/
    @PostMapping("update-photo")
    @ResponseBody
    public String updatePhoto(@RequestBody ArticlePhoto articlePhoto) {
        int res = articlePhotoService.updateById(articlePhoto);
        return res == 1 ? "success" : "fail";
    }
    /**
     * @Author Admin
     * @Description 删除对应的照片
     * @Date 16:49 2020/5/20
     * @param id
     * @return java.lang.String
     **/
    @GetMapping("delete-photo")
    @ResponseBody
    public String deletePhoto(String id) {
        int res = articlePhotoService.deleteById(id);
        return res == 1 ? "success" : "fail";
    }
}