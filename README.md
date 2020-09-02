# Animals
宠它
## 项目架构为 Maven + SpringBoot + Mybatis
前后端采用JSON作为交互数据格式，项目实现了图片视频的上传，实现了任务的发布及接受、评价，基于WebSocket的聊天功能、完成签到、发布动态、点赞关注、实名认证等操作。

app
	
  config: 项目的配置类  
		RedisConfig：redis的配置类
		SimpleCORSFilter：项目过滤器配置类
		WebMvcConfiguration: 项目拦截器的配置类
		WebSocketConfig: 项目的WebSocket配置类
    
	controller: 项目的前端控制器
  
		ArticleCommentController：文章评论控制器
		ArticleInfoController: 文章信息控制器
		ArticlePhotoController: 文章照片的控制器
		ArticleReplyController: 文章回复的控制器
		PassPortController: 项目的认中心控制器
		PetAdoptController：领养功能控制器
		PetFosterController：寄养功能控制器
		PetInfoController：宠物信息控制器
		PetShopHospitalController：宠物医院 宠物店的控制器
		PetTaskController： 用户任务的控制器
		PetTaskPhotoController：任务照片的控制器
		SignController： 签到的控制器
		SysController: 系统控制器
		SysCouponController：优惠劵的控制器
		SysNoticeController：消息通知的控制器
		SysNoticeWebSocket：点赞通知的WebSocket
		UserChatController：用户聊天的控制器
		UserController: 用户的控制器
		WebSocketOneToOne：用户聊天的WebSocket
    
	entity: 项目的实体类
  
		ArticleComment: 文章评论
		ArticleInfo；文章信息
		ArticlePhoto：文章照片
		ArticleReply：文章回复
		PetAdopt：用户领养
		PetFoster：用户寄养
		PetHabit：宠物习性
		PetHospitalShopPhoto： 宠物股医院、宠物店的照片
		PetInfo：宠物信息
		PetPhoto：宠物照片
		PetShopHospital：宠物店和宠物医院
		PetTaskPhoto：宠物任务照片
		SysCountOfDay：每天定时统计发表的博客数量
		SysCoupon：优惠劵
		SysCouponUser：用户与优惠劵关系
		SysNotice：用户的通知
		SysSign：用户的签到表
		UserArticlePraise；用户点赞
		UserAuthentication：用户实名认证
		UserBusinessLicense：用户营业执照
		UserChat：用户聊天
		UserFollow：用户的关注粉丝
		UserInfo：用户的基本信息
		UserTask：用户与任务
    
	interceptors: 项目拦截器
  
		AuthInterceptor：项目的具体拦截器实现
    
	service: 项目的后端业务
  
		ArticleCommentService: 文章评论业务接口
		ArticleInfoService：文章业务接口
		ArticlePhotoService：文章照片业务接口
		ArticleReplyService：文章回复业务接口
		PetAdoptService：领养业务接口
		PetFosterService：寄养业务接口
		PetHabitService：宠物习性业务接口
		PetHospitalShopPhotoService：宠物店和宠物医院照片业务接口
		PetInfoService：宠物信息的业务接口
		PetPhotoService：宠物的照片接口
		PetShopHospitalService：宠物店和宠物医院业务接口
		PetTaskPhotoService：任务照片的业务接口
		SignService：签到的业务接口
		SysCountOfDayService：每天定时统计发表的博客数量业务接口
		SysCouponService： 优惠劵的业务接口
		SysCouponUserService： 用户和优惠劵业务接口
		SysNoticeService：通知业务接口
		UserArticlePraiseService：用户点赞的业务接口
		UserAuthenticationService： 用户的实名认证业务接口
		UserBusinessLicenseService：用户的营业执照的业务接口
		UserChatService：用户聊天的业务接口
		UserFollowService: 用户关注的业务接口
		UserService： 用户的业务接口
		UserTaskService：用户与任务的业务接口
base: 项目的工具类

安装、配置服务端：

	1. 使用java -jar animal-0.0.1-SNAPSHOT.jar命令，运行项目jar包
	2. 如要使用该项目的日志功能， 需要在opt目录下新建animalLog文件夹使用，新建文件夹 命令为 mkdir animalLog

Read AUTHORS：Perry

联系方式：
	电子邮箱：13294578357@163.com
  手机号：13294578357
