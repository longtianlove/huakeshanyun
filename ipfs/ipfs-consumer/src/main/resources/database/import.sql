/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-10-01 14:26:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `geography_location` varchar(50) DEFAULT NULL COMMENT '地理位置',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统登录日志表';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('1', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 13:06:35', '2018-10-01 13:06:35');
INSERT INTO `sys_login_log` VALUES ('2', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:10:27', '2018-10-01 14:10:27');
INSERT INTO `sys_login_log` VALUES ('3', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:13:04', '2018-10-01 14:13:04');
INSERT INTO `sys_login_log` VALUES ('4', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:14:53', '2018-10-01 14:14:53');
INSERT INTO `sys_login_log` VALUES ('5', '9', 'test', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:15:59', '2018-10-01 14:15:59');
INSERT INTO `sys_login_log` VALUES ('6', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:16:22', '2018-10-01 14:16:22');
INSERT INTO `sys_login_log` VALUES ('7', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:16:34', '2018-10-01 14:16:34');
INSERT INTO `sys_login_log` VALUES ('8', '9', 'test', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:16:47', '2018-10-01 14:16:47');
INSERT INTO `sys_login_log` VALUES ('9', '1', 'admin', '127.0.0.1', 'XX XX 内网IP ', '2018-10-01 14:17:25', '2018-10-01 14:17:25');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `available` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  `permission_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `parent_id` int(10) DEFAULT NULL COMMENT '父权限ID',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '父权限ID列表',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '权限编码',
  `resource_type` varchar(50) DEFAULT NULL COMMENT '资源类型(directory/menu/button)',
  `url` varchar(50) DEFAULT NULL COMMENT '资源路径',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '1', '系统管理', '0', '0', 'system', 'directory', null, '2018-07-10 11:24:48', '2018-07-10 11:24:48');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户管理', '1', '0/1', 'user:view', 'menu', '/user/list', '2018-07-10 11:24:48', '2018-07-10 11:24:48');
INSERT INTO `sys_permission` VALUES ('3', '1', '用户添加', '2', '0/1/2', 'user:add', 'button', '', '2018-07-10 11:25:40', '2018-07-10 11:25:40');
INSERT INTO `sys_permission` VALUES ('4', '1', '用户删除', '2', '0/1/2', 'user:del', 'button', '', '2018-07-10 11:27:10', '2018-07-10 11:27:10');
INSERT INTO `sys_permission` VALUES ('5', '1', '用户编辑', '2', '0/1/2', 'user:edit', 'button', '', '2018-10-01 12:46:14', '2018-07-10 11:27:36');
INSERT INTO `sys_permission` VALUES ('6', '1', '角色管理', '1', '0/1', 'role:view', 'menu', '/role/list', '2018-08-04 09:38:44', '2018-08-04 09:38:44');
INSERT INTO `sys_permission` VALUES ('7', '1', '角色添加', '6', '0/1/6', 'role:add', 'button', '', '2018-08-04 09:42:05', '2018-08-04 09:42:05');
INSERT INTO `sys_permission` VALUES ('8', '1', '角色删除', '6', '0/1/6', 'role:del', 'button', '', '2018-08-04 09:43:26', '2018-08-04 09:43:26');
INSERT INTO `sys_permission` VALUES ('9', '1', '角色编辑', '6', '0/1/6', 'role:edit', 'button', '', '2018-08-04 09:46:01', '2018-08-04 09:46:01');
INSERT INTO `sys_permission` VALUES ('10', '1', '权限管理', '1', '0/1', 'permission:view', 'menu', '/permission/list', '2018-08-04 09:48:57', '2018-08-04 09:48:57');
INSERT INTO `sys_permission` VALUES ('11', '1', '权限添加', '10', '0/1/10', 'permission:add', 'button', '', '2018-08-04 09:50:50', '2018-08-04 09:50:50');
INSERT INTO `sys_permission` VALUES ('12', '1', '权限删除', '10', '0/1/10', 'permission:del', 'button', '', '2018-08-04 09:50:50', '2018-08-04 09:50:50');
INSERT INTO `sys_permission` VALUES ('13', '1', '权限编辑', '10', '0/1/10', 'permission:edit', 'button', '', '2018-08-23 11:33:34', '2018-08-23 11:33:34');
INSERT INTO `sys_permission` VALUES ('14', '1', '测试管理', '0', '0', 'test', 'directory', null, '2018-10-01 14:15:32', '2018-07-10 11:24:48');
INSERT INTO `sys_permission` VALUES ('15', '1', '一级菜单', '14', '0/14', 'onemenu:view', 'menu', '/onemenu/list', '2018-10-01 14:13:25', '2018-07-10 11:24:48');
INSERT INTO `sys_permission` VALUES ('16', '1', '登录日志', '1', '0/1', 'loginLog:view', 'menu', '/loginLog/list', '2018-10-01 12:25:02', '2018-10-01 12:25:02');
INSERT INTO `sys_permission` VALUES ('17', '1', '图标管理', '1', '0/1', 'icon:view', 'menu', '/icon/icons', '2018-10-01 12:48:42', '2018-08-23 13:15:51');
INSERT INTO `sys_permission` VALUES ('18', '1', '图标管理', '14', '0/14', 'icon:view', 'menu', '/icon/icons', '2018-10-01 14:15:26', '2018-08-23 13:15:51');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `permission_ids` varchar(255) DEFAULT NULL COMMENT '权限ID列表',
  `available` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编号',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', ',1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,', '1', '超级管理员', 'admin', '2018-07-10 11:19:49', '2018-07-10 11:19:49');
INSERT INTO `sys_role` VALUES ('2', ',14,15,18,', '1', '测试员', 'test', '2018-08-25 14:27:51', '2018-07-10 11:19:49');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(10) DEFAULT NULL COMMENT '角色ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态(0：禁用，1：启用，2：锁定)',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '超级管理员', 'admin', '90de4365c537fa959193d13ad8d07665', 'XZUY77Pq41M6jaGeR2q1yMaPOrmemy6A', '1', '2018-09-26 22:29:52', '2018-07-10 11:16:24');
INSERT INTO `sys_user` VALUES ('9', '2', 'test', 'test', 'f58cb4cbc689ace5456577d913c68bfd', 'tPKGoMIgl6y16wWaoaqXyRS2N3WzmsNo', '1', '2018-08-12 15:53:42', '2018-08-11 15:31:26');
