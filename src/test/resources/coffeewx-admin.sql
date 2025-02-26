/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : coffeewx-admin

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-03-15 12:14:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) DEFAULT '0' COMMENT '性别：0是男 1是女',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `flag` varchar(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'admin', 'a66abb5684c45962d887564f08346e8d', 'Kevin', '0', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '2019-01-10 17:05:28', '2019-02-26 15:47:39');
INSERT INTO `sys_user` VALUES ('14', 'test', '47ec2dd791e31e2ef2076caf64ed9b3d', '夕阳西下', '0', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '2019-02-26 15:48:20', '2019-02-26 15:48:20');

-- ----------------------------
-- Table structure for `t_wx_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_account`;
CREATE TABLE `t_wx_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '公众号名称',
  `account` varchar(100) DEFAULT NULL COMMENT '公众号账户',
  `appid` varchar(100) DEFAULT NULL COMMENT '公众号appid',
  `appsecret` varchar(100) DEFAULT NULL COMMENT '公众号密钥',
  `url` varchar(100) DEFAULT NULL COMMENT '公众号url',
  `token` varchar(100) DEFAULT NULL COMMENT '公众号token',
  `aeskey` varchar(300) DEFAULT NULL COMMENT '加密密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公众号账户表';

-- ----------------------------
-- Table structure for `t_wx_account_fans`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_account_fans`;
CREATE TABLE `t_wx_account_fans` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `subscribe_status` char(1) DEFAULT NULL COMMENT '订阅状态，0未关注，1已关注',
  `subscribe_time` datetime DEFAULT NULL COMMENT '订阅时间',
  `nickname` varbinary(2000) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别，1男，2女，0未知',
  `language` varchar(30) DEFAULT NULL COMMENT '语言',
  `country` varchar(30) DEFAULT NULL COMMENT '国家',
  `province` varchar(30) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `headimg_url` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信公众号ID',
  `wx_account_appid` varchar(100) DEFAULT NULL COMMENT '微信公众号appid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='微信公众号粉丝表';

-- ----------------------------
-- Table structure for `t_wx_fans_msg`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_fans_msg`;
CREATE TABLE `t_wx_fans_msg` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `nickname` varbinary(2000) DEFAULT NULL COMMENT '昵称',
  `headimg_url` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `msg_type` varchar(32) DEFAULT NULL COMMENT '消息类型',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `res_content` varchar(300) DEFAULT NULL COMMENT '最近一条回复内容',
  `is_res` varchar(32) DEFAULT NULL COMMENT '是否已回复',
  `media_id` varchar(100) DEFAULT NULL COMMENT '微信素材ID',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '微信图片URL',
  `pic_path` varchar(500) DEFAULT NULL COMMENT '本地图片路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='粉丝消息表 ';

-- ----------------------------
-- Table structure for `t_wx_fans_msg_res`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_fans_msg_res`;
CREATE TABLE `t_wx_fans_msg_res` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fans_msg_id` varchar(32) DEFAULT NULL COMMENT '粉丝消息ID',
  `res_content` varchar(300) DEFAULT NULL COMMENT '回复内容',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='回复粉丝消息历史表 ';

-- ----------------------------
-- Table structure for `t_wx_media_upload`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_media_upload`;
CREATE TABLE `t_wx_media_upload` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(500) DEFAULT NULL COMMENT '图片URL',
  `media_id` varchar(32) DEFAULT NULL COMMENT '素材ID',
  `thumb_media_id` varchar(32) DEFAULT NULL COMMENT '缩略图素材ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信素材上传表 ';

-- ----------------------------
-- Records of t_wx_media_upload
-- ----------------------------

-- ----------------------------
-- Table structure for `t_wx_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_menu`;
CREATE TABLE `t_wx_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(32) DEFAULT NULL COMMENT '菜单类型 1文本消息；2图文消息；3网址链接；4小程序',
  `menu_level` varchar(32) DEFAULT NULL COMMENT '菜单等级',
  `tpl_id` varchar(32) DEFAULT NULL COMMENT '模板ID',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `menu_sort` varchar(32) DEFAULT NULL COMMENT '排序',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `miniprogram_appid` varchar(32) DEFAULT NULL COMMENT '小程序appid',
  `miniprogram_pagepath` varchar(200) DEFAULT NULL COMMENT '小程序页面路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='微信菜单表';

-- ----------------------------
-- Table structure for `t_wx_news_article_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_news_article_item`;
CREATE TABLE `t_wx_news_article_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `digest` varchar(50) DEFAULT NULL COMMENT '摘要',
  `author` varchar(32) DEFAULT NULL COMMENT '作者',
  `show_cover_pic` char(1) DEFAULT NULL COMMENT '是否展示封面图片（0/1）',
  `thumb_media_id` varchar(50) DEFAULT NULL COMMENT '上传微信，封面图片标识',
  `content` varchar(3072) DEFAULT NULL COMMENT '内容',
  `content_source_url` varchar(50) DEFAULT NULL COMMENT '内容链接',
  `order_no` int(11) DEFAULT NULL COMMENT '文章排序',
  `pic_path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `need_open_comment` varchar(32) DEFAULT NULL COMMENT '是否可以留言',
  `only_fans_can_comment` varchar(32) DEFAULT NULL COMMENT '是否仅粉丝可以留言',
  `news_id` varchar(32) DEFAULT NULL COMMENT '图文ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='图文消息文章列表表 ';

-- ----------------------------
-- Table structure for `t_wx_news_template`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_news_template`;
CREATE TABLE `t_wx_news_template` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键 主键ID',
  `tpl_name` varchar(32) DEFAULT NULL COMMENT '模板名称',
  `is_upload` varchar(32) DEFAULT NULL COMMENT '是否已上传微信',
  `media_id` varchar(50) DEFAULT NULL,
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='图文消息模板表';


-- ----------------------------
-- Table structure for `t_wx_receive_text`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_receive_text`;
CREATE TABLE `t_wx_receive_text` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive_text` varchar(32) DEFAULT NULL COMMENT '关键字',
  `msg_type` varchar(32) DEFAULT NULL COMMENT '消息类型 1文本消息；2图文消息；',
  `tpl_id` varchar(32) DEFAULT NULL COMMENT '模板ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='回复关键字表';

-- ----------------------------
-- Table structure for `t_wx_subscribe_text`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_subscribe_text`;
CREATE TABLE `t_wx_subscribe_text` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `msg_type` varchar(32) DEFAULT NULL COMMENT '消息类型 1文本消息；2图文消息；',
  `tpl_id` varchar(32) DEFAULT NULL COMMENT '模板ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='关注欢迎语表';

-- ----------------------------
-- Records of t_wx_subscribe_text
-- ----------------------------
INSERT INTO `t_wx_subscribe_text` VALUES ('3', '1', '4', '1', '2019-01-23 11:01:15', '2019-01-23 13:16:24');

-- ----------------------------
-- Table structure for `t_wx_text_template`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_text_template`;
CREATE TABLE `t_wx_text_template` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tpl_name` varchar(32) DEFAULT NULL COMMENT '模板名字',
  `content` varchar(255) DEFAULT NULL COMMENT '模板内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='文本模板表';
