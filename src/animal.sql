/*
 Navicat Premium Data Transfer
 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 03/11/2020 11:53:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_info_id` int(11) NOT NULL COMMENT '文章的id',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论的内容',
  `comment_user_id` int(11) NOT NULL COMMENT '评论用户的id',
  `praise_num` int(11) NULL DEFAULT 0 COMMENT '点赞的数量',
  `create_time` datetime(0) NOT NULL COMMENT '评论创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_user_id` int(11) NOT NULL COMMENT '发表动态的用户id',
  `create_time` datetime(0) NOT NULL COMMENT '发表动态的时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '修改用户的id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改的日期',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客标题',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除 0表示不删除，1表示删除',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '文章的分类',
  `is_top` int(11) NULL DEFAULT 0 COMMENT '是否上推荐 0表示不上推荐，1表示上推荐',
  `is_original` int(11) NULL DEFAULT NULL COMMENT '是否是原创 0表示不是原创，1表示是原创',
  `is_private` int(11) NULL DEFAULT 0 COMMENT '是否是私有 0表示不是私有，1表示是私有',
  `praise_num` int(11) NULL DEFAULT 0 COMMENT '点赞数量',
  `forward_num` int(11) NULL DEFAULT 0 COMMENT '转发的数量',
  `pet_info_id` int(11) NULL DEFAULT NULL COMMENT '宠物的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_photo
-- ----------------------------
DROP TABLE IF EXISTS `article_photo`;
CREATE TABLE `article_photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_user_id` int(11) NOT NULL COMMENT '创建人的id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建的日期',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '修改人的id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改的日期',
  `picture_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片 视频的url',
  `article_info_id` int(11) NOT NULL COMMENT '文章的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_reply
-- ----------------------------
DROP TABLE IF EXISTS `article_reply`;
CREATE TABLE `article_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `comment_id` int(11) NOT NULL COMMENT '评论的id',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '回复的内容',
  `from_user_id` int(11) NOT NULL COMMENT '回复用户的id',
  `to_user_id` int(11) NOT NULL COMMENT '目标用户的id',
  `praise_num` int(11) NULL DEFAULT 0 COMMENT '点赞的数量',
  `create_time` datetime(0) NOT NULL COMMENT '回复创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user`;
CREATE TABLE `coupon_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `to_user_id` int(11) NOT NULL COMMENT '用户的id',
  `coupon_id` int(11) NOT NULL COMMENT '优惠劵的id',
  `create_user_id` int(11) NOT NULL COMMENT '发布优惠劵人的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_adopt
-- ----------------------------
DROP TABLE IF EXISTS `pet_adopt`;
CREATE TABLE `pet_adopt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pet_type` int(11) NOT NULL DEFAULT 2 COMMENT '宠物领养1,寄养为2',
  `create_date` date NOT NULL COMMENT '任务开始时间',
  `end_date` date NULL DEFAULT NULL COMMENT '任务结束的时间',
  `from_user_id` int(11) NOT NULL COMMENT '创建任务的用户id',
  `to_user_id` int(11) NULL DEFAULT NULL COMMENT '接受任务的用户id',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '对此任务的描述',
  `evaluate` int(11) NULL DEFAULT NULL COMMENT '对此任务的评价 1代表一星 2代表二星依次类推',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '此任务的价格',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布此任务的城市',
  `type` int(11) NOT NULL DEFAULT 2 COMMENT '0 代表没有创建 1代表成功 2 代表正在进行中 3结束 4代表需要评价',
  `pet_info_id` int(11) NOT NULL COMMENT '宠物id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_foster
-- ----------------------------
DROP TABLE IF EXISTS `pet_foster`;
CREATE TABLE `pet_foster`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pet_type` int(11) NOT NULL DEFAULT 1 COMMENT '宠物领养1,寄养为2',
  `create_date` date NOT NULL COMMENT '任务开始时间',
  `end_date` date NOT NULL COMMENT '任务结束的时间',
  `from_user_id` int(11) NOT NULL COMMENT '创建任务的用户id',
  `to_user_id` int(11) NULL DEFAULT NULL COMMENT '接受任务的用户id',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '对此任务的描述',
  `evaluate` int(11) NULL DEFAULT NULL COMMENT '对此任务的评价 1代表一星 2代表二星依次类推',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '此任务的价格',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布此任务的城市',
  `pet_info_id` int(11) NOT NULL COMMENT '宠物的id',
  `type` int(11) NOT NULL DEFAULT 2 COMMENT '0代表失败 1代表成功 2代表正在进行中 3 已结束代表未评价价 4代表结束了已评价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_habit
-- ----------------------------
DROP TABLE IF EXISTS `pet_habit`;
CREATE TABLE `pet_habit`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `pet_info_id` int(32) NOT NULL COMMENT '宠物的id',
  `pet_select_skill` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '挑选的技巧',
  `pet_feed_points` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '喂食宠物的要点',
  `pet_train_skill` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '训练宠物的技巧',
  `pet_common_illness` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宠物常见的疾病',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_hospital_shop_photo
-- ----------------------------
DROP TABLE IF EXISTS `pet_hospital_shop_photo`;
CREATE TABLE `pet_hospital_shop_photo`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `photo` int(11) NOT NULL,
  `pet_shop_hospital_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_info
-- ----------------------------
DROP TABLE IF EXISTS `pet_info`;
CREATE TABLE `pet_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` int(11) NULL DEFAULT NULL COMMENT '宠物领养为1，寄养为2',
  `pet_photo_id` int(11) NULL DEFAULT NULL COMMENT '宠物图片的url',
  `kind` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宠物的品种',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物的名字',
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宠物的年龄 0 母 1公',
  `sex` int(11) NULL DEFAULT NULL COMMENT '宠物的性别',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '该宠物暂无描述信息' COMMENT '宠物的描述',
  `user_info_id` int(11) NULL DEFAULT NULL COMMENT '用户的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_photo
-- ----------------------------
DROP TABLE IF EXISTS `pet_photo`;
CREATE TABLE `pet_photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物的照片 视频',
  `pet_info_id` int(11) NOT NULL COMMENT '宠物的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_shop_hospital
-- ----------------------------
DROP TABLE IF EXISTS `pet_shop_hospital`;
CREATE TABLE `pet_shop_hospital`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物医院或宠物店名称',
  `type` int(2) NOT NULL COMMENT '医院为1，宠物店为2',
  `location` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院或者宠物店的位置',
  `evaluate` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院或者宠物店的评价',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '该医院暂时没有描述' COMMENT '医院或者宠物店的描述',
  `create_user_id` int(11) NOT NULL COMMENT '医院或者宠物店创始人',
  `license` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院或者宠物店的营业执照',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院或者宠物店联系电话',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院或者宠物店qq',
  `wx` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院或者宠物店wx',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet_task_photo
-- ----------------------------
DROP TABLE IF EXISTS `pet_task_photo`;
CREATE TABLE `pet_task_photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` int(11) NOT NULL COMMENT '宠物领养2,寄养为1',
  `pet_task_id` int(11) NOT NULL COMMENT '任务的id',
  `photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务的照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_count_of_day
-- ----------------------------
DROP TABLE IF EXISTS `sys_count_of_day`;
CREATE TABLE `sys_count_of_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_info_id` int(11) NOT NULL COMMENT '博客id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `count` int(11) NULL DEFAULT NULL COMMENT '每天发表博客次数',
  `create_time` date NULL DEFAULT NULL COMMENT '当前日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sys_coupon`;
CREATE TABLE `sys_coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠劵的描述',
  `create_date` datetime(0) NOT NULL COMMENT '优惠劵的创建日期',
  `end_date` datetime(0) NOT NULL COMMENT '优惠劵的截止日期',
  `type` int(11) NOT NULL COMMENT '0表示医院，1表示宠物店',
  `coupon_pet_id` int(11) NULL DEFAULT NULL COMMENT '优惠宠物的id',
  `create_user_id` int(11) NOT NULL COMMENT '优惠劵创建人的id',
  `pet_shop_hospital_id` int(11) NOT NULL COMMENT '医院或者宠物店的id',
  `num` int(11) NOT NULL DEFAULT 0 COMMENT '优惠劵的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `from_user_id` int(11) NOT NULL COMMENT '点赞用户id',
  `to_user_id` int(11) NOT NULL COMMENT '被点赞的用户id',
  `article_info_id` int(11) NULL DEFAULT NULL COMMENT '被点赞的文章id',
  `is_read` int(11) NULL DEFAULT 0 COMMENT '是否已读',
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论的内容',
  `type` int(11) NOT NULL COMMENT '1 点赞 2关注 3 评论 4回复 5取消关注 6取消点赞 7寄养 8领养',
  `is_success` int(11) NULL DEFAULT 0 COMMENT '1成功 0失败',
  `from_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞用户名字',
  `to_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被点赞的用户名字',
  `create_time` datetime(0) NOT NULL COMMENT '创建的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 167 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_sign
-- ----------------------------
DROP TABLE IF EXISTS `sys_sign`;
CREATE TABLE `sys_sign`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `count_sign` int(11) NULL DEFAULT 0 COMMENT '统计的次数',
  `mark` int(11) NULL DEFAULT 0 COMMENT '标记是否签到 1代表签到 0代表未签到',
  `monday` int(11) NULL DEFAULT 0 COMMENT '标记周一是否签到',
  `tuesday` int(11) NULL DEFAULT 0,
  `wednesday` int(11) NULL DEFAULT 0,
  `thursday` int(11) NULL DEFAULT 0,
  `friday` int(11) NULL DEFAULT 0,
  `saturday` int(11) NULL DEFAULT 0,
  `sunday` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_article_praise
-- ----------------------------
DROP TABLE IF EXISTS `user_article_praise`;
CREATE TABLE `user_article_praise`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_info_id` int(11) NOT NULL COMMENT '文章id',
  `user_info_id` int(11) NOT NULL COMMENT '用户的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_authentication
-- ----------------------------
DROP TABLE IF EXISTS `user_authentication`;
CREATE TABLE `user_authentication`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `id_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `bank_card_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的地址',
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的性别',
  `birthday` date NOT NULL COMMENT '用户的出生年月日',
  `nation` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的民族',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行预留手机号',
  `id_card_photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证照片',
  `user_info_id` int(11) NOT NULL COMMENT '用户信息id',
  `is_update` int(11) NOT NULL DEFAULT 0 COMMENT '是否已经更新了',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_business_license
-- ----------------------------
DROP TABLE IF EXISTS `user_business_license`;
CREATE TABLE `user_business_license`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `work_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位的类型',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位的法人',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位的地址',
  `effective_time` date NOT NULL COMMENT '有效期',
  `certificate_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编号',
  `business_license_photo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业执照的照片',
  `user_info_id` int(11) NOT NULL COMMENT '用户信息id',
  `is_update` int(11) NULL DEFAULT 0 COMMENT '是否已经更新',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_chat
-- ----------------------------
DROP TABLE IF EXISTS `user_chat`;
CREATE TABLE `user_chat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `from_user_id` int(11) NOT NULL COMMENT '发送用户的id',
  `to_user_id` int(11) NOT NULL COMMENT '接收用户的id',
  `chat_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话的id',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会话内容',
  `read` int(11) NOT NULL DEFAULT 0 COMMENT '标识已读 1为已读 0为未读',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `from_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送人的名字',
  `to_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人的名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `follow_user_id` int(11) NOT NULL COMMENT '被关注人的id',
  `fan_user_id` int(11) NOT NULL COMMENT '关注人的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `is_personal` int(11) NOT NULL DEFAULT 1 COMMENT '0代表非个人，1代表个人',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的手机号',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的地址',
  `birthday` date NULL DEFAULT NULL COMMENT '用户的生日',
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别 ',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在城市',
  `job` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户职业',
  `integral` int(11) NULL DEFAULT 0 COMMENT '用户积分',
  `vip` int(11) NOT NULL DEFAULT 0 COMMENT '是否是会员   1： 会员  2表示非会员',
  `vip_start_date` datetime(0) NULL DEFAULT NULL COMMENT '会员开始日期',
  `vip_end_date` datetime(0) NULL DEFAULT NULL COMMENT '会员截止日期',
  `photo` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'http://39.96.42.195/group1/M00/00/00/rBF7517BK_WAKWz3AAAgIJZDFTM158.png' COMMENT '用户头像',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户qq',
  `wx` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户wx',
  `is_authentication` int(11) NOT NULL DEFAULT 0 COMMENT '是否实名认证 1 实名认证 0没有实名认证',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机号唯一标识',
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名唯一标识',
  UNIQUE INDEX `email`(`email`) USING BTREE COMMENT '用户邮箱唯一标识'
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_task
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_id` int(11) NOT NULL COMMENT '创建任务的id',
  `to_user_id` int(11) NOT NULL COMMENT '想要完成任务的id',
  `type` int(11) NOT NULL COMMENT '0 寄养 1 代表领养 ',
  `from_user_id` int(11) NULL DEFAULT NULL COMMENT '创建任务的用户id',
  `is_success` int(11) NOT NULL DEFAULT 0 COMMENT '是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
