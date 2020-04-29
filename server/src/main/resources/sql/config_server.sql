/*
Navicat MySQL Data Transfer

Source Server         : localhost_1
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : config_server

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-04-29 10:33:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                          `group_id` varchar(200) DEFAULT NULL,
                          `data_id` varchar(200) DEFAULT NULL,
                          `content` text,
                          `created_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`),
                          KEY `group_id` (`group_id`),
                          KEY `data_id` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='配置表';

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', 'test_group', 'test_data1', 'test.value=2001', '2019-03-13 14:10:06', '2019-03-22 09:53:11');
INSERT INTO `config` VALUES ('2', 'test_group', 'test_data2', 'test.name=haha', '2019-03-16 10:08:14', '2019-03-16 10:08:17');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(20) NOT NULL COMMENT '用户名',
                        `password` varchar(50) DEFAULT NULL COMMENT '密码',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3');
