/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : coffeewx-admin

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-04-02 14:20:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `level` int(11) DEFAULT NULL COMMENT '部门等级',
  `order_no` int(11) DEFAULT NULL COMMENT '部门排序',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限管理-部门表 ';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级资源ID',
  `resources` varchar(50) DEFAULT NULL COMMENT '资源编码',
  `title` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `level` int(11) DEFAULT NULL COMMENT '资源级别',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `type` varchar(32) DEFAULT NULL COMMENT '类型 menu、button',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='权限管理-权限资源表 ';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', 'sysUserManage', '系统管理', '1', '3', 'component', 'menu', '', '2019-03-28 18:51:08', '2019-03-28 18:51:10');
INSERT INTO `sys_permission` VALUES ('2', '1', 'userList', '用户管理', '2', '1', 'my-user', 'menu', '', '2019-03-28 18:52:13', '2019-03-30 14:20:45');
INSERT INTO `sys_permission` VALUES ('3', '2', 'userList:add', '添加', '3', '1', 'el-icon-edit', 'button', '', '2019-03-28 18:53:31', '2019-04-01 20:19:55');
INSERT INTO `sys_permission` VALUES ('4', '2', 'userList:edit', '编辑', '3', '2', null, 'button', '', '2019-03-28 18:54:26', '2019-04-01 20:20:16');
INSERT INTO `sys_permission` VALUES ('5', '2', 'userList:delete', '删除', '3', '3', null, 'button', '', '2019-03-28 18:55:25', '2019-04-01 20:20:09');
INSERT INTO `sys_permission` VALUES ('6', '0', 'wxmpSystemManage', '微信管理', '1', '1', 'list', 'menu', '', '2019-03-29 13:29:17', '2019-03-29 13:29:19');
INSERT INTO `sys_permission` VALUES ('7', '6', 'accountList', '账号管理', '2', '1', 'wechat', 'menu', '', '2019-03-30 00:26:50', '2019-03-30 00:26:53');
INSERT INTO `sys_permission` VALUES ('8', '6', 'subscribeTextList', '欢迎语管理', '2', '2', 'guide', 'menu', '', '2019-03-30 12:04:57', '2019-03-30 12:04:57');
INSERT INTO `sys_permission` VALUES ('9', '6', 'receiveTextList', '关键字管理', '2', '3', 'icon-receive-text', 'menu', '', '2019-03-30 13:46:11', '2019-03-30 13:46:11');
INSERT INTO `sys_permission` VALUES ('10', '6', 'menuList', '公众号菜单', '2', '4', 'tree', 'menu', '', '2019-03-30 13:47:02', '2019-04-01 19:23:25');
INSERT INTO `sys_permission` VALUES ('11', '6', 'accountFansList', '粉丝管理', '2', '5', 'peoples', 'menu', '', '2019-03-30 13:47:35', '2019-03-30 13:52:26');
INSERT INTO `sys_permission` VALUES ('12', '6', 'fansMsgList', '粉丝消息', '2', '6', 'message', 'menu', '', '2019-03-30 13:48:11', '2019-03-30 13:52:36');
INSERT INTO `sys_permission` VALUES ('13', '0', 'wxmpMaterialManage', '素材管理', '1', '2', 'table', 'menu', '', '2019-03-30 13:54:43', '2019-03-30 13:54:43');
INSERT INTO `sys_permission` VALUES ('14', '13', 'textTemplateList', '文本管理', '2', '1', 'documentation', 'menu', '', '2019-03-30 13:57:07', '2019-03-30 13:57:07');
INSERT INTO `sys_permission` VALUES ('15', '13', 'newsTemplateList', '图文管理', '2', '2', 'icon-news', 'menu', '', '2019-03-30 13:58:00', '2019-03-30 13:58:00');
INSERT INTO `sys_permission` VALUES ('16', '1', 'roleList', '角色管理', '2', '2', 'my-role', 'menu', '', '2019-03-30 14:00:03', '2019-03-30 14:20:59');
INSERT INTO `sys_permission` VALUES ('17', '1', 'sysmenuList', '菜单管理', '2', '3', 'my-sysmenu', 'menu', '', '2019-03-30 14:00:53', '2019-03-30 14:21:10');
INSERT INTO `sys_permission` VALUES ('19', '7', 'accountList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 19:24:55', '2019-04-01 19:24:55');
INSERT INTO `sys_permission` VALUES ('20', '7', 'accountList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 19:25:20', '2019-04-01 19:25:20');
INSERT INTO `sys_permission` VALUES ('21', '7', 'accountList:delete', '删除', '3', '3', '', 'button', '', '2019-04-01 19:25:48', '2019-04-01 19:25:48');
INSERT INTO `sys_permission` VALUES ('22', '7', 'accountList:generateQR', '生成二维码', '3', '4', '', 'button', '', '2019-04-01 19:26:28', '2019-04-01 19:26:28');
INSERT INTO `sys_permission` VALUES ('23', '8', 'subscribeTextList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:10:24', '2019-04-01 20:10:24');
INSERT INTO `sys_permission` VALUES ('24', '8', 'subscribeTextList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 20:10:48', '2019-04-01 20:10:48');
INSERT INTO `sys_permission` VALUES ('25', '8', 'subscribeTextList:delete', '删除', '3', '3', '', 'button', '', '2019-04-01 20:11:12', '2019-04-01 20:11:12');
INSERT INTO `sys_permission` VALUES ('26', '9', 'receiveTextList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:11:39', '2019-04-01 20:11:39');
INSERT INTO `sys_permission` VALUES ('27', '9', 'receiveTextList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 20:11:57', '2019-04-01 20:11:57');
INSERT INTO `sys_permission` VALUES ('28', '9', 'receiveTextList:delete', '删除', '3', '3', '', 'button', '', '2019-04-01 20:12:12', '2019-04-01 20:12:12');
INSERT INTO `sys_permission` VALUES ('29', '10', 'menuList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:13:07', '2019-04-01 20:13:07');
INSERT INTO `sys_permission` VALUES ('30', '10', 'menuList:addsub', '添加下级', '3', '2', '', 'button', '', '2019-04-01 20:13:29', '2019-04-01 20:13:29');
INSERT INTO `sys_permission` VALUES ('31', '10', 'menuList:edit', '编辑', '3', '3', '', 'button', '', '2019-04-01 20:13:51', '2019-04-01 20:13:51');
INSERT INTO `sys_permission` VALUES ('32', '10', 'menuList:delete', '删除', '3', '4', '', 'button', '', '2019-04-01 20:14:08', '2019-04-01 20:14:08');
INSERT INTO `sys_permission` VALUES ('33', '11', 'accountFansList:delete', '删除', '3', '1', '', 'button', '', '2019-04-01 20:14:50', '2019-04-01 20:14:50');
INSERT INTO `sys_permission` VALUES ('34', '12', 'fansMsgList:delete', '删除', '3', '1', '', 'button', '', '2019-04-01 20:15:42', '2019-04-01 20:15:42');
INSERT INTO `sys_permission` VALUES ('35', '14', 'textTemplateList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:16:06', '2019-04-01 20:16:06');
INSERT INTO `sys_permission` VALUES ('36', '14', 'textTemplateList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 20:16:24', '2019-04-01 20:16:24');
INSERT INTO `sys_permission` VALUES ('37', '14', 'textTemplateList:delete', '删除', '3', '3', '', 'button', '', '2019-04-01 20:16:40', '2019-04-01 20:16:40');
INSERT INTO `sys_permission` VALUES ('38', '15', 'newsTemplateList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:17:54', '2019-04-01 20:17:54');
INSERT INTO `sys_permission` VALUES ('39', '15', 'newsTemplateList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 20:18:12', '2019-04-01 20:18:12');
INSERT INTO `sys_permission` VALUES ('40', '15', 'newsTemplateList:editnews', '编辑图文', '3', '3', '', 'button', '', '2019-04-01 20:18:36', '2019-04-01 20:18:36');
INSERT INTO `sys_permission` VALUES ('41', '15', 'newsTemplateList:preview', '预览', '3', '4', '', 'button', '', '2019-04-01 20:19:11', '2019-04-01 20:19:11');
INSERT INTO `sys_permission` VALUES ('42', '15', 'newsTemplateList:delete', '删除', '3', '5', '', 'button', '', '2019-04-01 20:19:31', '2019-04-01 20:19:31');
INSERT INTO `sys_permission` VALUES ('43', '16', 'roleList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:20:46', '2019-04-01 20:20:46');
INSERT INTO `sys_permission` VALUES ('44', '16', 'roleList:edit', '编辑', '3', '2', '', 'button', '', '2019-04-01 20:21:03', '2019-04-01 20:21:03');
INSERT INTO `sys_permission` VALUES ('45', '16', 'roleList:rolesetting', '权限设置', '3', '3', '', 'button', '', '2019-04-01 20:21:24', '2019-04-01 20:21:24');
INSERT INTO `sys_permission` VALUES ('46', '16', 'roleList:delete', '删除', '3', '4', '', 'button', '', '2019-04-01 20:21:55', '2019-04-01 20:21:55');
INSERT INTO `sys_permission` VALUES ('47', '17', 'sysmenuList:add', '添加', '3', '1', '', 'button', '', '2019-04-01 20:22:31', '2019-04-01 20:22:31');
INSERT INTO `sys_permission` VALUES ('48', '17', 'sysmenuList:addsub', '添加下级', '3', '2', '', 'button', '', '2019-04-01 20:23:00', '2019-04-01 20:23:00');
INSERT INTO `sys_permission` VALUES ('49', '17', 'sysmenuList:edit', '编辑', '3', '3', '', 'button', '', '2019-04-01 20:23:28', '2019-04-01 20:23:28');
INSERT INTO `sys_permission` VALUES ('50', '17', 'sysmenuList:delete', '删除', '3', '4', '', 'button', '', '2019-04-01 20:23:46', '2019-04-01 20:23:46');
INSERT INTO `sys_permission` VALUES ('51', '11', 'accountFansList:quickres', '快捷回复', '3', '2', '', 'button', '', '2019-04-02 10:48:16', '2019-04-02 10:48:16');
INSERT INTO `sys_permission` VALUES ('52', '12', 'fansMsgList:quickres', '快捷回复', '3', '2', '', 'button', '', '2019-04-02 10:49:12', '2019-04-02 10:49:12');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限管理-角色表 ';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '系统管理员', '系统管理员', '2019-03-28 15:51:56', '2019-03-28 15:51:59');
INSERT INTO `sys_role` VALUES ('2', 'visitor', '访客', '访客', '2019-03-28 20:17:04', '2019-03-28 20:17:06');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限资源ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='权限管理-角色-权限资源关联表 ';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('41', '1', '6', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('42', '1', '7', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('43', '1', '19', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('44', '1', '20', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('45', '1', '21', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('46', '1', '22', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('47', '1', '8', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('48', '1', '23', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('49', '1', '24', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('50', '1', '25', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('51', '1', '9', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('52', '1', '26', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('53', '1', '27', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('54', '1', '28', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('55', '1', '10', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('56', '1', '29', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('57', '1', '30', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('58', '1', '31', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('59', '1', '32', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('60', '1', '11', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('61', '1', '33', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('62', '1', '12', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('63', '1', '34', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('64', '1', '13', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('65', '1', '14', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('66', '1', '35', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('67', '1', '36', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('68', '1', '37', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('69', '1', '15', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('70', '1', '38', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('71', '1', '39', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('72', '1', '40', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('73', '1', '41', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('74', '1', '42', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('75', '1', '1', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('76', '1', '2', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('77', '1', '3', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('78', '1', '4', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('79', '1', '5', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('80', '1', '16', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('81', '1', '43', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('82', '1', '44', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('83', '1', '45', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('84', '1', '46', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('85', '1', '17', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('86', '1', '47', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('87', '1', '48', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('88', '1', '49', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('89', '1', '50', '2019-04-01 20:46:21', '2019-04-01 20:46:21');
INSERT INTO `sys_role_permission` VALUES ('181', '2', '6', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('182', '2', '7', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('183', '2', '8', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('184', '2', '9', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('185', '2', '10', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('186', '2', '11', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('187', '2', '51', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('188', '2', '12', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('189', '2', '52', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('190', '2', '13', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('191', '2', '14', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('192', '2', '15', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('193', '2', '1', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('194', '2', '2', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('195', '2', '16', '2019-04-02 10:53:06', '2019-04-02 10:53:06');
INSERT INTO `sys_role_permission` VALUES ('196', '2', '17', '2019-04-02 10:53:06', '2019-04-02 10:53:06');

-- ----------------------------
-- Table structure for `sys_role_wxaccount`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_wxaccount`;
CREATE TABLE `sys_role_wxaccount` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='权限资源-角色微信账户关联表 ';


-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `pwd` varchar(200) DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) DEFAULT '0' COMMENT '性别：0是男 1是女',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `flag` varchar(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限管理-用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', 'Kevin', '0', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '2019-01-10 17:05:28', '2019-04-02 14:17:18');
INSERT INTO `sys_user` VALUES ('2', 'test', '47ec2dd791e31e2ef2076caf64ed9b3d', '夕阳西下', '0', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '2019-02-26 15:48:20', '2019-04-02 14:17:32');

-- ----------------------------
-- Table structure for `sys_user_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源-用户部门关联表 ';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限管理-用户角色关联表 ';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '2019-03-28 15:53:32', '2019-03-28 15:53:35');
INSERT INTO `sys_user_role` VALUES ('5', '2', '2', '2019-04-02 12:09:47', '2019-04-02 12:09:47');

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
  `qr_url` varchar(200) DEFAULT NULL COMMENT '二维码图片URL',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公众号账户表';


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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='微信公众号粉丝表';

-- ----------------------------
-- Table structure for `t_wx_account_fans_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_account_fans_tag`;
CREATE TABLE `t_wx_account_fans_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `tag_id` varchar(32) DEFAULT NULL COMMENT '标签ID',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='粉丝标签关联表';

-- ----------------------------
-- Records of t_wx_account_fans_tag
-- ----------------------------

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
  `res_content` text COMMENT '最近一条回复内容',
  `is_res` varchar(32) DEFAULT NULL COMMENT '是否已回复',
  `media_id` varchar(100) DEFAULT NULL COMMENT '微信素材ID',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '微信图片URL',
  `pic_path` varchar(500) DEFAULT NULL COMMENT '本地图片路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='粉丝消息表 ';

-- ----------------------------
-- Table structure for `t_wx_fans_msg_res`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_fans_msg_res`;
CREATE TABLE `t_wx_fans_msg_res` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fans_msg_id` varchar(32) DEFAULT NULL COMMENT '粉丝消息ID',
  `res_content` text COMMENT '回复内容',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='回复粉丝消息历史表 ';


-- ----------------------------
-- Table structure for `t_wx_fans_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_fans_tag`;
CREATE TABLE `t_wx_fans_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '标签名称',
  `count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `wx_account_id` varchar(32) DEFAULT NULL COMMENT '微信账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='粉丝标签表';


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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='微信菜单表';


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
  `content` text COMMENT '内容',
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='图文消息文章列表表 ';

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='图文消息模板表';

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
