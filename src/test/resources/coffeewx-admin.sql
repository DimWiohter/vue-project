/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : coffeewx-admin

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-01-23 16:51:04
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'Kevin', '0', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '2019-01-10 17:05:28', '2019-01-10 17:05:31');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公众号账户表';

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='微信公众号粉丝表';

-- ----------------------------
-- Table structure for `t_wx_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_menu`;
CREATE TABLE `t_wx_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(32) DEFAULT NULL COMMENT '菜单类型 1文本消息；2图文消息；3网址链接',
  `menu_level` varchar(32) DEFAULT NULL COMMENT '菜单等级',
  `tpl_id` varchar(32) DEFAULT NULL COMMENT '模板ID',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `menu_sort` varchar(32) DEFAULT NULL COMMENT '排序',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='微信菜单表';


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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='回复关键字表';


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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='关注欢迎语表';


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
