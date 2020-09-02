package com.liu.animal;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//@Slf4j
//public class AnimalApplicationTests {
//    @Autowired
//    private RedisUtil redisUtil;
//    @Autowired
//    private RedissonClient redissonClient;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserAuthenticationService userAuthenticationService;
//    @Autowired
//    private UserChatService userChatService;
//    @Autowired
//    private UserBusinessLicenseService userBusinessLicenseService;
//    @Autowired
//    private SignService signService;
//    @Autowired
//    private SignMapper signMapper;
//    @Autowired
//    private ArticleInfoService articleInfoService;
//    @Autowired
//    private SysNoticeService sysNoticeService;
//    @Autowired
//    private ArticleCommentService articleCommentService;
//    @Autowired
//    private PetInfoService petInfoService;
//    @Autowired
//    private PetTaskService petTaskService;
//
//
//
//    @Test
//    public void contextLoads() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId("3");
//        userInfo.setUsername("流水");
//        userInfo.setPassword("123123");
//        userInfo.setPhone("13294571289");
//        userInfo.setAddress("山西太原");
//        UserInfo userInfo1 = userService.findById("15");
//        System.out.println(userInfo1.getUsername());
////        Date date = new Date();
////        userInfo.setBirthday(date);
////        userInfo.setVipStartDate(date);
////        userInfo.setSex("1");
////        System.out.println(userInfo.getSex());
////        int res = userChatService.updateByChatId();
////        System.out.println(11111);
////        int res = userService.addById(userInfo);
//////        int res = userService.updateById(userInfo);
//////        System.out.println(res);
//////        int res = userService.deleteById("2");
////        System.out.println(res);
//    }
//    @Test
//    public void testUserChat(){
//        UserChat userChat = new UserChat();
//        userChat.setChatId("5");
//        userChat.setContent("99");
//        int res = userChatService.updateByChatId(userChat);
//        System.out.println("res = " + res);
//    }
//    @Test
//    public void testUserAuthentication(){
//        UserAuthentication userAuthentication = new UserAuthentication();
//        userAuthentication.setUsername("你好");
//        userAuthenticationService.addInfo(userAuthentication);
//    }
//    @Test
//    public void testUserBusinessLicense(){
//        UserBusinessLicense userBusinessLicense = new UserBusinessLicense();
//        userBusinessLicense.setUsername("111");
//        userBusinessLicenseService.addInfo(userBusinessLicense);
//        System.out.println(111);
//    }
//    @Test
//    public void testRedis(){
//        Jedis jedis = redisUtil.getJedis();
////        UserInfo userInfo = new UserInfo();
////        userInfo.setPhone("12348891256");
////        userInfo.setSex("1");
////        userInfo.setUsername("123456");
////        String json = JSON.toJSONString(userInfo);
////        jedis.set("user",json);
////        jedis.setex("user",60 * 60 * 2,"json");
////        jedis.del("user");
////        String user = jedis.get("user");
////        UserInfo userInfo1 = JSONObject.parseObject(user, UserInfo.class);
////        System.out.println(userInfo1.getUsername());
////        System.out.println(userInfo1.getPhone());
////        jedis.set("123","1");
////        jedis.incrBy("123",1);
////        System.out.println(jedis.get("123"));
//        jedis.flushDB();
//    }
//    @Test
//    public void log(){
//        log.info("你好");
//    }
//    @Test
//    public void Sgin(){
////        signMapper.truncate();
////        System.out.println(signMapper.selectYeaterday("sunday"));
////        signService.Sgin("1");
//
//        SysSign sysSign = signService.findById("2");
//        System.out.println(sysSign.getSunday());
//
//    }
//    @Test
//    public void Article(){
//        ArticleInfo articleInfo = new ArticleInfo();
//        articleInfo.setId("1");
//
//        articleInfo.setCreateUserId("17");
//        articleInfo.setContent("测试sdasdasd");
//        articleInfo.setTitle("测试");
//        articleInfo.setType("测试类");
//        articleInfo.setPetInfoId("1");
//        ArticlePhoto articlePhoto1 = new ArticlePhoto();
//        articlePhoto1.setPictureUrl("1111");
//        ArticlePhoto articlePhoto2 = new ArticlePhoto();
//        articlePhoto2.setPictureUrl("22222");
//        ArticlePhoto articlePhoto3 = new ArticlePhoto();
//        articlePhoto3.setPictureUrl("3333");
//        List<ArticlePhoto> articlePhotoList = new ArrayList<>();
//        articlePhotoList.add(articlePhoto1);
//        articlePhotoList.add(articlePhoto2);
//        articlePhotoList.add(articlePhoto3);
//        articleInfo.setPhotoList(articlePhotoList);
//        articleInfo.setId("15");
//        articleInfoService.deleteById("16");
//    }
//    @Test
//    public void testNotice(){
////        SysNotice sysNotice = new SysNotice();
////        sysNotice.setFromUserId("1");
////        sysNotice.setArticleInfoId("1");
//        System.out.println(sysNoticeService.findByUserId("1"));
//    }
//    @Test
//    public void testComment(){
//        ArticleComment articleComment = new ArticleComment();
//        articleComment.setArticleInfoId("1");
//        articleComment.setContent("你好啊");
////        articleComment.setPraiseNum(1);
////        articleCommentService.findById("1");
//        articleCommentService.addInfo(articleComment);
//    }
//    @Test
//    public void testTask() throws Exception {
//        PetTask petTask = new PetTask();
//        petTask.setPetType("1");
//        petTask.setCreateDate(new Date());
//        String endDate = "2020-5-15";
//        Date date = DateUtils.StringToDate(endDate, "yyyy-MM-dd");
//        petTask.setEndDate(date);
//        petTask.setFromUserId("14");
//        petTask.setDescription("测试");
//        petTask.setCity("山西 太原");
//        petTask.setPetInfoId("1");
//        List<PetTask> all = petTaskService.findAll(1, 10);
//        for (PetTask task : all) {
//            System.out.println(task.getPetTypeStr());
//        }
//    }
//    @Test
//    public void testPetInfo(){
////        PetInfo petInfo = new PetInfo();
////        petInfo.setType("1");
////        petInfo.setPetPhotoId("2");
////        petInfo.setKind("金毛");
////        petInfo.setName("test");
////        petInfo.setDescription("测试");
////        petInfoService.findById("2");
//        System.out.println(Integer.parseInt("0"));
//    }
//    @Test
//    public void test01() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("测试");
//        userInfo.setPassword("123456");
//        userInfo.setSex("1");
//        userInfo.setDescription("测试数据");
//        userService.register(userInfo);
//    }
//}
