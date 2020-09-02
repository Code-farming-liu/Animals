package com.liu.animal;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.SignMapper;
import com.liu.animal.app.service.*;
import com.liu.animal.base.util.Base64Util;
import com.liu.animal.base.util.FileUtil;
import com.liu.animal.base.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

///**
// * @ClassName: Test
// * @Description: TODO
// * @Author: Admin
// * @Date 2020/5/13 12:03
// **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class TestAnimal {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private UserChatService userChatService;
    @Autowired
    private UserBusinessLicenseService userBusinessLicenseService;
    @Autowired
    private SignService signService;
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetTaskPhotoService petTaskPhotoService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private PetAdoptService petAdoptService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private ArticlePhotoService articlePhotoService;
    @Autowired
    private PetPhotoService petPhotoService;
    @Autowired
    private ArticleReplyService articleReplyService;

    //
//    @Test
//    public void test01() {
//        UserInfo userInfo = new UserInfo();
////        userInfo.setUsername("刘瑞");
////        userInfo.setPassword("123123");
////        userInfo.setSex("1");
////        userInfo.setDescription("测试数据");
//        userInfo.setId("17");
////        userInfo.setAddress("山西临汾");
//        userService.deleteById("15");
//
//    }
//
    @Test
    public void test02() throws IOException {
        // 本地文件路径
        String filePath = "D:\\5.jpg";
        byte[] bytes1 = FileUtil.readFileByBytes("d:\\2.jpg");//生活照
        byte[] bytes2 = FileUtil.readFileByBytes("d:\\1.jpg");//身份证的照片
        String image1 = Base64Util.encode(bytes1);
        String image2 = Base64Util.encode(bytes2);
        System.out.println(image1);
        UserAuthentication userAuthentication = new UserAuthentication();

//        userAuthentication.setEmail("2258076697@qq.com");
//        userAuthentication.setBankCardNumber("123456789525125");
//        userAuthentication.setUsername("刘瑞");
//        userAuthentication.setIdNumber("142630199910181213");
        userAuthentication.setImage(image2);
        userAuthentication.setLiveImage1(image1);
        userAuthentication.setIdCardImage2(image2);
//        userAuthentication.setUserInfoId("17");
        userAuthenticationService.authentication(userAuthentication);
//        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(image1);
//        System.out.println(multipartFile);
    }

    //
//    @Test
//    public void test03() {
//        SysNotice sysNotice = new SysNotice();
//        sysNotice.setId("32");
//        sysNotice.setIsRead("1");
//        sysNotice.setCreateTime(new Date());
//        sysNoticeService.addInfo(sysNotice);
//    }
//
//    @Test
//    public void test04() {
////       petFosterService.findByFromUserId("14");
////       petAdoptService.findByFromUserId("14");
////        List<PetAdopt> byToUserId = petAdoptService.findByToUserId("3");
////        petFosterService.findByToUserId("3");
//        List<PetFoster> endByToUserId = petFosterService.findEndByToUserId("3");
//        for (PetFoster petFoster : endByToUserId) {
//
//        }
//        List<PetAdopt> endByToUserId1 = petAdoptService.findEndByToUserId("3");
//        for (PetAdopt petAdopt : endByToUserId1) {
//
//        }
//    }
//
    @Test
    public void test05() {
        PetFoster petFoster = new PetFoster();
        petFoster.setCreateDate(new Date());
        petFoster.setEndDate(new Date());
        petFoster.setFromUserId("14");
        petFoster.setToUserId("14");
        petFoster.setDescription("测试啊");
        petFoster.setCity("城市");
        petFoster.setPetInfoId("1");
        petFoster.setId("1");
        petFoster.setEvaluate("4");
        List<PetTaskPhoto> petTaskPhotos = new ArrayList<>();
        PetTaskPhoto petTaskPhoto1 = new PetTaskPhoto();
        petTaskPhoto1.setPhoto("11111");
        PetTaskPhoto petTaskPhoto2 = new PetTaskPhoto();
        petTaskPhoto2.setPhoto("11111");
        PetTaskPhoto petTaskPhoto3 = new PetTaskPhoto();
        petTaskPhoto3.setPhoto("11111");
        PetTaskPhoto petTaskPhoto4 = new PetTaskPhoto();
        petTaskPhoto4.setPhoto("11111");
        petTaskPhotos.add(petTaskPhoto1);
        petTaskPhotos.add(petTaskPhoto2);
        petTaskPhotos.add(petTaskPhoto3);
        petTaskPhotos.add(petTaskPhoto4);
        petFoster.setPetTaskPhotoList(petTaskPhotos);
//        petFosterService.addInfo(petFoster);
//        PetAdopt petAdopt = new PetAdopt();
//        petFosterService.deleteById("4");
        PetAdopt petAdopt = new PetAdopt();
        petAdopt.setCreateDate(new Date());
        petAdopt.setEndDate(new Date());
        petAdopt.setFromUserId("17");
        petAdopt.setToUserId("14");
        petAdopt.setDescription("测试啊");
        petAdopt.setCity("城市");
        petAdopt.setPetInfoId("1");
        petAdopt.setType("1");
        petAdopt.setId("1");
        petAdopt.setEvaluate("5");
        petAdopt.setPetTaskPhotoList(petTaskPhotos);
//        petAdoptService.addInfo(petAdopt);
////        petAdoptService.updateById(petAdopt);
        petFosterService.deleteById("7");
    }

    //
//    @Test
//    public void test07() {
//        UserTask userTask = new UserTask();
//        userTask.setTaskId("5");
//        userTask.setType("2");
//        userTask.setFromUserId("17");
//        userTask.setToUserId("14");
//        userTask.setIsSuccess("1");
//        userTaskService.updateById(userTask);
//    }
//
//    @Test
//    public void test08() {
//        ArticlePhoto articlePhoto = new ArticlePhoto();
//        articlePhoto.setPictureUrl("sfadfsdfsdgsdf");
//        articlePhoto.setArticleInfoId("1");
//        articlePhoto.setCreateDate(new Date());
//        articlePhotoService.updateById(articlePhoto);
//    }
//
//    @Test
//    public void test09() {
//        PetInfo petInfo = new PetInfo();
//        petInfo.setName("测试");
//        petInfo.setDescription("测试");
//        petInfo.setUserInfoId("17");
//        petInfo.setKind("金毛");
//        petInfo.setId("1");
//        petInfoService.deleteById("1");
//    }
//
//    @Test
//    public void test10() {
//        PetPhoto petPhoto = new PetPhoto();
//        petPhoto.setPetInfoId("1");
//        petPhoto.setPhoto("22222");
//        petPhoto.setPetInfoId("1");
//        petPhotoService.deleteById("1");
//    }
//
    @Test
    public void test11() {
        PetTaskPhoto petTaskPhoto = new PetTaskPhoto();
        petTaskPhoto.setPetTaskId("1");
        petTaskPhoto.setPhoto("1233");
        petTaskPhoto.setType("1");
        petTaskPhotoService.updateById(petTaskPhoto);
    }

    @Test
    public void test12() {
//        userService.emailCode("2258076697@qq.com");
        UserChat userChat = new UserChat();
        userChat.setRead("0");
        userChat.setChatId("14_22");
        articleInfoService.findHotInfo();
    }

    @Test
    public void Article() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId("1");
        articleInfo.setCreateUserId("22");
        articleInfo.setContent("测试sdasdasd");
        articleInfo.setTitle("测试");
        articleInfo.setType("测试类");
        articleInfo.setPetInfoId("1");
        ArticlePhoto articlePhoto1 = new ArticlePhoto();
        articlePhoto1.setPictureUrl("1111");
        ArticlePhoto articlePhoto2 = new ArticlePhoto();
        articlePhoto2.setPictureUrl("22222");
        ArticlePhoto articlePhoto3 = new ArticlePhoto();
        articlePhoto3.setPictureUrl("3333");
        List<ArticlePhoto> articlePhotoList = new ArrayList<>();
        articlePhotoList.add(articlePhoto1);
        articlePhotoList.add(articlePhoto2);
        articlePhotoList.add(articlePhoto3);
        articleInfo.setPhotoList(articlePhotoList);
        signService.sgin("22");
    }
    @Test
    public void test01(){
        ArticleReply articleReply = new ArticleReply();
        articleReply.setCommentId("1");
        articleReply.setCreateTime(new Date());
        articleReply.setToUserId("1");
        articleReply.setFromUserId("1");
        articleReply.setContent("11111");
//        articleReplyService.addInfo(articleReply);
//        petFosterService.findWantUser("5","1");
        articleInfoService.findHotInfo();
    }

}