package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.SysNoticeMapper;
import com.liu.animal.app.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: SysNoticeServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 14:46
 **/
@Service
@Slf4j
@Transactional
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserArticlePraiseService userArticlePraiseService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private ArticleReplyService articleReplyService;
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private UserFollowService userFollowService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private PetAdoptService petAdoptService;

    @Override
    public List<SysNotice> findByUserId(String userId) {
        UserInfo userInfo = null;
        List<SysNotice> sysNoticeList = null;
        try {
            sysNoticeList = sysNoticeMapper.selectByUserId(userId);
        } catch (Exception e) {
            log.error("点赞 根据用户id查找失败" + e.getMessage());
            return null;
        }
        for (SysNotice sysNotice : sysNoticeList) {
            UserInfo userInfo1 = userService.findById(sysNotice.getFromUserId());
            sysNotice.setUserInfo(userInfo1);
            ArticleInfo articleInfo = articleInfoService.findById(sysNotice.getArticleInfoId());
            sysNotice.setArticleInfo(articleInfo);
        }
        Collections.sort(sysNoticeList, new Comparator<SysNotice>() {
            @Override
            public int compare(SysNotice o1, SysNotice o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return sysNoticeList;
    }

    @Override
    public SysNotice findEndNotice() {
        try {
            return sysNoticeMapper.selectEndNotice();
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的findEndNotice出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<SysNotice> findAll(Integer page, Integer num) {
        List<SysNotice> sysNoticeList = null;

        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(sysNoticeMapper.count());
            } else {
                page = (page - 1) * num;
            }
            sysNoticeList = sysNoticeMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的findAll出错" + e.getMessage());
        }
        for (SysNotice sysNotice : sysNoticeList) {
            UserInfo userInfo1 = userService.findById(sysNotice.getFromUserId());
            sysNotice.setUserInfo(userInfo1);
            ArticleInfo articleInfo = articleInfoService.findById(sysNotice.getArticleInfoId());
            sysNotice.setArticleInfo(articleInfo);
        }
        Collections.sort(sysNoticeList, new Comparator<SysNotice>() {
            @Override
            public int compare(SysNotice o1, SysNotice o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return sysNoticeList;
    }

    @Override
    public int addInfo(SysNotice sysNotice) {
        try {
            sysNoticeMapper.insertInfo(sysNotice);
            return 1;
        } catch (Exception e) {
            log.error("保存点赞信息出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(SysNotice sysNotice) {
        try {
            return sysNoticeMapper.updateById(sysNotice);
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return sysNoticeMapper.deleteById(id);
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public SysNotice findById(String id) {
        SysNotice sysNotice = null;
        try {
            sysNotice = sysNoticeMapper.selectById(id);
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的findById出错" + e.getMessage());
            return null;
        }
        String str = sysNotice.getType();
        ArticleComment articleComment = new ArticleComment();
        ArticleReply articleReply = new ArticleReply();
        ArticleInfo articleInfo = new ArticleInfo();
        UserFollow userFollow = new UserFollow();
        PetAdopt petAdopt = new PetAdopt();
        PetFoster petFoster = new PetFoster();
        String key = null;
        UserArticlePraise userArticlePraise = new UserArticlePraise();
        int praiseNum = 0;
        switch (str) {
            case "1":
                praiseNum = articleInfoService.praiseNum(sysNotice, articleInfo, userArticlePraise);
                break;
            case "2":
                userFollow.setFollowUserId(sysNotice.getToUserId());
                userFollow.setFanUserId(sysNotice.getFromUserId());
                userFollowService.addInfo(userFollow);
                break;
            case "3":
                articleComment.setArticleInfoId(sysNotice.getArticleInfoId());
                articleComment.setContent(sysNotice.getContent());
                articleComment.setCommentUserId(sysNotice.getFromUserId());
                articleCommentService.addInfo(articleComment);
                break;
            case "4":
                articleReply.setCommentId(sysNotice.getArticleInfoId());
                articleReply.setContent(sysNotice.getContent());
                articleReply.setFromUserId(sysNotice.getFromUserId());
                articleReply.setToUserId(sysNotice.getToUserId());
                articleReplyService.addInfo(articleReply);
                break;
            case "5":
                userFollow.setFanUserId(sysNotice.getFromUserId());
                userFollow.setFollowUserId(sysNotice.getToUserId());
                userFollowService.deleteByFollowUserId(userFollow);
                break;
            case "6":
                praiseNum = articleInfoService.praiseNum(sysNotice, articleInfo, userArticlePraise);
                break;
            default:
                break;
        }
        return sysNotice;
    }


    @Override
    public String count() {
        try {
            return sysNoticeMapper.count();
        } catch (Exception e) {
            log.error("SysNoticeServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}