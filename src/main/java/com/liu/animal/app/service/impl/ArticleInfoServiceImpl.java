package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.ArticleInfoMapper;
import com.liu.animal.app.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: ArticleInfoService
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 9:56
 **/
@Service
@Transactional
@Slf4j
public class ArticleInfoServiceImpl implements ArticleInfoService {
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Autowired
    private ArticlePhotoService articlePhotoService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private ArticleReplyService articleReplyService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysCountOfDayService sysCountOfDayService;
    @Autowired
    private UserArticlePraiseService userArticlePraiseService;
    @Autowired
    private PetInfoService petInfoService;


    @Override
    public List<ArticleInfo> findAll(Integer page, Integer num) {
        List<ArticleInfo> articleInfos = null;
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(articleInfoMapper.count());
            } else {
                page = (page - 1) * num;
            }
            articleInfos = articleInfoMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findAll出错" + e.getMessage());
        }
        for (ArticleInfo articleInfo : articleInfos) {
//            isTop(articleInfo);
            ArticleInfo articleInfo1 = articleInfoService.findById(articleInfo.getId());
            articleInfoList.add(articleInfo1);
        }
        Collections.sort(articleInfoList, new Comparator<ArticleInfo>() {
            @Override
            public int compare(ArticleInfo o1, ArticleInfo o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return articleInfoList;
    }

    public void isTop(ArticleInfo articleInfo) {
        String total = userService.count();
        Integer count1 = (int) (Integer.parseInt(total) * 0.8);
        Integer count2 = (int) (Integer.parseInt(total) * 0.6);
        UserInfo userInfo2 = userService.findById(articleInfo.getCreateUserId());
        if (StringUtils.isNotBlank(userInfo2.getVip())) {
            if (userInfo2.getVip().equals("0")) {
                List<ArticleInfo> articleInfoList = articleInfoService.findIsTopArticle(count1);
                for (ArticleInfo articleInfo1 : articleInfoList) {
                    if (articleInfo1.getId().equals(articleInfo.getId())) {
                        ArticleInfo articleInfo2 = new ArticleInfo();
                        articleInfo2.setIsTop("1");
                        articleInfo2.setId(articleInfo.getId());
                        articleInfoService.updateById(articleInfo2);
                        UserInfo userInfo = userService.findById(articleInfo.getCreateUserId());
                        UserInfo userInfo1 = new UserInfo();
                        Integer integral = userInfo.getIntegral();
                        integral += 2;
                        userInfo1.setIntegral(integral);
                        userInfo1.setId(articleInfo.getCreateUserId());
                        userService.updateById(userInfo1);
                    } else {
                        List<ArticleInfo> articleInfos1 = articleInfoService.findIsTopArticle(count2);
                        for (ArticleInfo articleInfo2 : articleInfos1) {
                            if (articleInfo.getId().equals(articleInfo1.getId())) {
                                ArticleInfo articleInfo3 = new ArticleInfo();
                                articleInfo3.setId(articleInfo.getId());
                                articleInfo3.setIsTop("1");
                                articleInfoService.updateById(articleInfo3);
                                UserInfo userInfo = userService.findById(articleInfo.getCreateUserId());
                                UserInfo userInfo1 = new UserInfo();
                                Integer integral = userInfo.getIntegral();
                                integral += 2;
                                userInfo1.setIntegral(integral);
                                userInfo1.setId(articleInfo.getCreateUserId());
                                userService.updateById(userInfo1);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int addInfo(ArticleInfo articleInfo) {
//        SysCountOfDay sysCountOfDay1 = sysCountOfDayService.findById(articleInfo.getCreateUserId());
//        String key = articleInfo.getCreateUserId() + ":" + "count:of:Day";
//        Jedis jedis = redisUtil.getJedis();
//        Integer count = 0;
//        if (jedis.exists(key)) {
//            count = Integer.parseInt(jedis.get(key));
//        }
//        sysCountOfDay1.setCount(count + "");
//        if (sysCountOfDay1 != null) {
//            if (sysCountOfDay1.getCount().equals("4")) {
//                return 0;
//            }
//        }
//        try {
//            articleInfo.setCreateTime(new Date());
//            articleInfoMapper.insertInfo(articleInfo);
//        } catch (Exception e) {
//            log.error("service保存文章信息出错" + e.getMessage());
//            return 0;
//        }
//        List<ArticlePhoto> photoList = articleInfo.getPhotoList();
//        for (ArticlePhoto articlePhoto : photoList) {
//            articlePhoto.setArticleInfoId(articleInfo.getId());
//            try {
//                articlePhotoService.addInfo(articlePhoto);
//            } catch (Exception e) {
//                log.error("保存文章照片出现错误" + e.getMessage());
//                return 0;
//            }
//        }
//        SysCountOfDay sysCountOfDay = new SysCountOfDay();
//        sysCountOfDay.setArticleInfoId(articleInfo.getId());
//        sysCountOfDay.setUserId(articleInfo.getCreateUserId());
//        sysCountOfDay.setCreateTime(articleInfo.getCreateTime());
//        sysCountOfDayService.addInfo(sysCountOfDay);
        return 0;
    }

    @Override
    public String addArticleInfo(ArticleInfo articleInfo) {
        List<SysCountOfDay> sysCountOfDayList = sysCountOfDayService.findByUserId(articleInfo.getCreateUserId());
        if (sysCountOfDayList != null) {
            if (sysCountOfDayList.size() >= 3) {
                return "今日次数已达上限";
            }
        }
        try {
            articleInfo.setCreateTime(new Date());
            articleInfoMapper.insertInfo(articleInfo);
        } catch (Exception e) {
            log.error("service保存文章信息出错" + e.getMessage());
            return "fail";
        }
        ArticleInfo articleInfo1 = articleInfoMapper.selectLastArticle();
        List<ArticlePhoto> photoList = articleInfo.getPhotoList();
        for (ArticlePhoto articlePhoto : photoList) {
            articlePhoto.setArticleInfoId(articleInfo1.getId());
            articlePhoto.setCreateUserId(articleInfo1.getCreateUserId());
            articlePhoto.setCreateDate(new Date());
            try {
                articlePhotoService.addInfo(articlePhoto);
            } catch (Exception e) {
                log.error("保存文章照片出现错误" + e.getMessage());
                return "fail";
            }
        }
        SysCountOfDay sysCountOfDay = new SysCountOfDay();
        sysCountOfDay.setArticleInfoId(articleInfo1.getId());
        sysCountOfDay.setUserId(articleInfo1.getCreateUserId());
        sysCountOfDay.setCreateTime(articleInfo1.getCreateTime());
        sysCountOfDayService.addInfo(sysCountOfDay);
        UserInfo userInfo = userService.findById(articleInfo1.getCreateUserId());
        Integer integral = userInfo.getIntegral();
        UserInfo userInfo1 = new UserInfo();
        integral += 1;
        userInfo1.setIntegral(integral);
        userInfo1.setId(articleInfo.getCreateUserId());
        userService.updateById(userInfo1);
        return "success";
    }

    @Override
    public List<ArticleInfo> findArticleByPetInfoId(String petInfoId) {
        List<ArticleInfo> articleInfoList = null;
        List<ArticleInfo> articleInfoList1 = new ArrayList<>();
        try {
            articleInfoList = articleInfoMapper.selectArticleByPetInfoId(petInfoId);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findArticleByPetInfoId出错" + e.getMessage());
        }
        for (ArticleInfo articleInfo : articleInfoList) {
            ArticleInfo articleInfo1 = articleInfoService.findById(articleInfo.getId());
            PetInfo petInfo = petInfoService.findById(articleInfo1.getId());
            articleInfo.setPetInfo(petInfo);
            articleInfoList1.add(articleInfo1);
        }
        return articleInfoList1;
    }

    @Override
    public List<ArticleInfo> findIsTopArticle(Integer count) {
        List<ArticleInfo> articleInfoList = null;
        try {
            articleInfoList = articleInfoMapper.selectIsTopArticle(count);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findIsTopArticle出错" + e.getMessage());
            return null;
        }
        return articleInfoList;
    }

    @Override
    public List<ArticleInfo> findArticleByCreateUserId(String userId) {
        List<ArticleInfo> articleInfoList = null;
        List<ArticleInfo> articleInfoList1 = new ArrayList<>();
        try {
            articleInfoList = articleInfoMapper.selectArticleByCreateUserId(userId);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findArticleByCreateUserId出错" + e.getMessage());
        }
        for (ArticleInfo articleInfo : articleInfoList) {
            ArticleInfo articleInfo1 = articleInfoService.findById(articleInfo.getId());
            articleInfoList1.add(articleInfo1);
        }
        Collections.sort(articleInfoList1, new Comparator<ArticleInfo>() {
            @Override
            public int compare(ArticleInfo o1, ArticleInfo o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return articleInfoList1;
    }

    @Override
    public List<List<ArticleInfo>> findFollowInfo(String userId) {
        List<List<ArticleInfo>> res = new ArrayList<>();
        List<UserInfo> userInfoList = userService.findFollow(userId);
        for (UserInfo userInfo : userInfoList) {
            if (userInfo != null) {
                List<ArticleInfo> articleInfoList = articleInfoService.findArticleByCreateUserId(userInfo.getId());
                if (articleInfoList.size() != 0) {
                    res.add(articleInfoList);
                }
            }
        }
        return res;
    }

    @Override
    public List<ArticleInfo> findHotInfo() {
        List<ArticleInfo> articleInfoList = null;
        List<ArticleInfo> articleInfoList1 = new ArrayList<>();
        try {
            String count = articleInfoMapper.count();
            int res = (int) (Integer.parseInt(count) * 0.3);
            articleInfoList = articleInfoMapper.selectIsTopArticle(res);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findHotInfo出错" + e.getMessage());
        }
        for (ArticleInfo articleInfo : articleInfoList) {
            ArticleInfo articleInfo1 = articleInfoService.findById(articleInfo.getId());
            articleInfoList1.add(articleInfo1);
        }
        Collections.sort(articleInfoList1, new Comparator<ArticleInfo>() {
            @Override
            public int compare(ArticleInfo o1, ArticleInfo o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        });
        return articleInfoList1;
    }

    @Override
    public int updateById(ArticleInfo articleInfo) {
        try {
            return articleInfoMapper.updateById(articleInfo);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        ArticleInfo articleInfo = articleInfoService.findById(id);
        List<ArticleComment> articleCommentList = articleInfo.getCommentList();
        articleCommentService.deleteByArticleId(id);
        for (ArticleComment articleComment : articleCommentList) {
            articleReplyService.deleteByCommentId(articleComment.getId());
        }
        try {
            articleInfoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
        return 1;
    }

    /**
     * @param id
     * @return com.liu.animal.app.entity.ArticleInfo
     * @Author Admin
     * @Description 查询当前文章的所有评论和回复
     * @Date 14:59 2020/5/8
     **/
    @Override
    public ArticleInfo findById(String id) {
        ArticleInfo articleInfo = null;
        try {
            articleInfo = articleInfoMapper.selectById(id);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findById出错" + e.getMessage());
        }
        if (articleInfo != null) {
            UserInfo userInfo = userService.findById(articleInfo.getCreateUserId());
            if (userInfo != null) {
                articleInfo.setCreateUserName(userInfo.getUsername());
            }
            articleInfo.setUserInfo(userInfo);
            List<ArticleComment> articleCommentList = articleCommentService.findByArticleInfoId(id);
            articleInfo.setCommentCount(articleCommentList.size() + "");
            for (ArticleComment articleComment : articleCommentList) {
                UserInfo userInfo1 = userService.findById(articleComment.getCommentUserId());
                if (userInfo1 != null) {
                    articleComment.setCommentUserName(userInfo1.getUsername());
                    articleComment.setUserInfo(userInfo1);
                }
                List<ArticleReply> articleReplyList = articleReplyService.findByCommentId(articleComment.getId());
                articleComment.setReplyCount(articleReplyList.size() + "");
                for (ArticleReply articleReply : articleReplyList) {
                    UserInfo userInfo2 = userService.findById(articleReply.getFromUserId());
                    if (userInfo2 != null) {
                        articleReply.setFromUserStr(userInfo2.getUsername());
                    }
                    UserInfo userInfo3 = userService.findById(articleReply.getToUserId());
                    if (userInfo3 != null) {
                        articleReply.setToUserStr(userInfo3.getUsername());
                    }
                    articleReply.setUserInfo(userInfo2);
                }
                articleComment.setReplyList(articleReplyList);
            }
            articleInfo.setCommentList(articleCommentList);
            List<ArticlePhoto> articlePhotoList = articlePhotoService.findByArticleInfoId(articleInfo.getId());
            articleInfo.setPhotoList(articlePhotoList);
        }
        return articleInfo;
    }

    @Override
    public String count() {
        try {
            return articleInfoMapper.count();
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<Integer> findPraise(String userId) {
        List<UserArticlePraise> articleInfoRes = null;
        List<Integer> list = new ArrayList<>();
        try {
            articleInfoRes = articleInfoMapper.selectPraise(userId);
        } catch (Exception e) {
            log.error("ArticleInfoServiceImpl的findPraise出错" + e.getMessage());
        }
        for (UserArticlePraise articleInfoRe : articleInfoRes) {
            list.add(Integer.parseInt(articleInfoRe.getArticleInfoId()));
        }
        return list;
    }

//    @Override
//    public List<ArticleInfo> findPraiseAndFollow(String userId) {
//        List<Integer> articleInfoRes = articleInfoService.findPraise(userId);
//        List<UserInfo> userRes = userService.findFollow(userId);
//        List<ArticleInfo> articleInfoListRes = new ArrayList<>();
//        List<ArticleInfo> articleInfoList = articleInfoService.findAll(null, null);
//        for (ArticleInfo articleInfo : articleInfoList) {
//            ArticleInfo articleInfo1 = articleInfoService.findById(articleInfo.getId());
//            if (articleInfo.getId() != null) {
//                for (Integer articleInfoRe : articleInfoRes) {
//                    if (articleInfoRe == Integer.parseInt(articleInfo.getId())) {
//                        articleInfo1.setIsPraise("1");
//                        break;
//                    }
//                }
//            }
//            if (articleInfo1.getCreateUserId() != null) {
//                for (UserInfo userRe : userRes) {
//                    if (userRe.getId().equals(articleInfo.getCreateUserId())) {
//                        articleInfo1.setIsFollow("1");
//                        break;
//                    }
//                }
//            }
//            articleInfoListRes.add(articleInfo1);
//        }
//        Collections.sort(articleInfoListRes, new Comparator<ArticleInfo>() {
//            @Override
//            public int compare(ArticleInfo o1, ArticleInfo o2) {
//                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
//            }
//        });
//        return articleInfoListRes;
//    }

    @Override
    public int praiseNum(SysNotice sysNotice, ArticleInfo articleInfo, UserArticlePraise userArticlePraise) {
        String type = sysNotice.getType();
        ArticleInfo articleInfo1 = articleInfoService.findById(sysNotice.getArticleInfoId());
        ArticleInfo articleInfo2 = new ArticleInfo();
        articleInfo2.setId(articleInfo1.getId());
        int praiseNum = articleInfo1.getPraiseNum();
        if (type.equals("1")) {
            userArticlePraise.setUserInfoId(sysNotice.getFromUserId());
            userArticlePraise.setArticleInfoId(sysNotice.getArticleInfoId());
            userArticlePraiseService.addInfo(userArticlePraise);
            articleInfo2.setPraiseNum(++praiseNum);
            articleInfoService.updateById(articleInfo2);
        } else {
            userArticlePraise.setUserInfoId(sysNotice.getFromUserId());
            userArticlePraise.setArticleInfoId(sysNotice.getArticleInfoId());
            userArticlePraiseService.deletePraiseNum(userArticlePraise);
            articleInfo2.setPraiseNum(--praiseNum);
            articleInfoService.updateById(articleInfo2);
        }
        return praiseNum;
    }

//    @Override
//    public int removePraiseNum(SysNotice sysNotice, ArticleInfo articleInfo, String key, UserArticlePraise userArticlePraise) {
//        Jedis jedis = redisUtil.getJedis();
//        int praiseNum;
//        userArticlePraise.setUserInfoId(sysNotice.getFromUserId());
//        userArticlePraise.setArticleInfoId(sysNotice.getArticleInfoId());
//        userArticlePraiseService.deleteByArticleId(userArticlePraise);
//        praiseNum = 0;
//        if (jedis.exists(key)) {
//
//            praiseNum = Integer.parseInt(jedis.get(key));
//        } else {
//            //设置三天过期
//            jedis.setex(key, 60 * 60 * 24 * 3, "1");
//            articleInfo.setId(sysNotice.getArticleInfoId());
//            articleInfo.setPraiseNum(praiseNum);
//            try {
//                articleInfoService.updateById(articleInfo);
//            } catch (Exception e) {
//                log.error("文章取消点赞更新错误" + e.getMessage());
//            }
//        }
//        return praiseNum;
//    }
}