/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : slp

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 04/05/2020 13:06:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出勤ID',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_a_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_a_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生/教师出勤' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for examination
-- ----------------------------
DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试ID',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试名称',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_exam_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_exam_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考试信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for examination_score
-- ----------------------------
DROP TABLE IF EXISTS `examination_score`;
CREATE TABLE `examination_score`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生成绩ID',
  `student_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生ID',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学科',
  `score` decimal(65, 2) NOT NULL COMMENT '分数',
  `exam_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属考试ID',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_es_student_id`(`student_id`) USING BTREE,
  INDEX `fk_es_exam_id`(`exam_id`) USING BTREE,
  CONSTRAINT `fk_es_exam_id` FOREIGN KEY (`exam_id`) REFERENCES `examination` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_es_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考试分数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for learning_content
-- ----------------------------
DROP TABLE IF EXISTS `learning_content`;
CREATE TABLE `learning_content`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容ID',
  `subject_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容所属科目',
  `content_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容URI',
  `extension_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件扩展名',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `mime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传的文件MIME类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容名',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_lc_subject_id`(`subject_id`) USING BTREE,
  CONSTRAINT `fk_lc_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学习内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志内容',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '触发用户',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_log_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('08f0a5fc-7070-4915-985f-fc89c7bda724', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[a26f17ee-f8e9-43b4-adfc-15361551b37a]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:04.583000', '2020-05-04 13:06:04.583000');
INSERT INTO `log` VALUES ('227bd21b-d74c-4db2-800c-99a18bc29206', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[dd516c87-a215-4692-9133-229432465426]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:07.685000', '2020-05-04 13:06:07.685000');
INSERT INTO `log` VALUES ('4a7aa859-6162-47ab-94e7-a33374bc83c2', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[58037417-5f8e-4008-b2d3-a3bd52e2af27]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:12.121000', '2020-05-04 13:06:12.121000');
INSERT INTO `log` VALUES ('7409f205-0c6b-4780-86ea-2ff910c3a393', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[77e4a0c0-db55-4574-902c-0879448f1261]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:13.352000', '2020-05-03 22:17:13.352000');
INSERT INTO `log` VALUES ('7c78399c-a3f2-4931-9e9b-f1e29d4e9b1a', '╔═══════════════════════════════════\n║admin(ID:c)	新增教师	\n║调用参数：[teacher2][teacher2]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=UserDTO(id=dd516c87-a215-4692-9133-229432465426, name=teacher2, username=teacher2, freeze=false, roleId=b, gmtCreate=Mon May 04 11:10:20 CST 2020, gmtModified=Mon May 04 11:10:20 CST 2020))\n╚═══════════════════════════════════', 'c', '2020-05-04 11:10:20.586000', '2020-05-04 11:10:20.586000');
INSERT INTO `log` VALUES ('bd21c11d-4c30-41eb-9943-a8eb2f85e677', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[7fbc73ce-c716-436f-aa0c-ac67e35c95ac]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:15.635000', '2020-05-03 22:17:15.635000');
INSERT INTO `log` VALUES ('c08345bb-a3e1-4326-b9d4-2ace8b1c10a6', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[0ecd02fc-c1c7-4afe-b440-598ed8dc097b]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:10.712000', '2020-05-03 22:17:10.712000');
INSERT INTO `log` VALUES ('da0b791a-3f66-4589-b627-a5cb14baac0d', '╔═══════════════════════════════════\n║admin(ID:c)	新增教师	\n║调用参数：[教师测试账户1][teacher]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=UserDTO(id=a26f17ee-f8e9-43b4-adfc-15361551b37a, name=教师测试账户1, username=teacher, freeze=false, roleId=b, gmtCreate=Sun May 03 22:18:19 CST 2020, gmtModified=Sun May 03 22:18:19 CST 2020))\n╚═══════════════════════════════════', 'c', '2020-05-03 22:18:20.035000', '2020-05-03 22:18:20.035000');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_name`(`name`) USING BTREE COMMENT '角色名唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('a', '管理员', '2020-05-01 10:42:47.000000', '2020-05-01 10:50:10.319800');
INSERT INTO `role` VALUES ('b', '教师', '2020-05-01 10:50:22.000000', '2020-05-01 10:50:17.000000');
INSERT INTO `role` VALUES ('c', '学生', '2020-05-01 10:50:33.000000', '2020-05-01 10:50:48.000000');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生ID',
  `student_class_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '该学生所属班级',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `fk_student_student_class_id`(`student_class_id`) USING BTREE,
  CONSTRAINT `fk_student_student_class_id` FOREIGN KEY (`student_class_id`) REFERENCES `student_class` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_student_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属教师',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_student_class_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_student_class_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生班级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_learning
-- ----------------------------
DROP TABLE IF EXISTS `student_learning`;
CREATE TABLE `student_learning`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习ID',
  `learning_content_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生所学习的内容ID',
  `student_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生ID',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_sl_student_id`(`student_id`) USING BTREE,
  INDEX `fk_sl_lc_id`(`learning_content_id`) USING BTREE,
  CONSTRAINT `fk_sl_lc_id` FOREIGN KEY (`learning_content_id`) REFERENCES `learning_content` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_sl_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生学习' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work
-- ----------------------------
DROP TABLE IF EXISTS `student_work`;
CREATE TABLE `student_work`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '该作业所属学生学习ID',
  `file_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作业文件URI',
  `mime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件MIME类型',
  `extension_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件扩展名',
  `score` tinyint(4) NULL DEFAULT NULL COMMENT '教师评分',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `suggest` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '教师建议',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fk_sw_id` FOREIGN KEY (`id`) REFERENCES `student_learning` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生作业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目ID',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属教师',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目名',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_subject_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_subject_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '科目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `freeze` bit(1) NOT NULL DEFAULT b'0' COMMENT '冻结账户',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'MD5盐',
  `role_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属角色',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE COMMENT '用户名唯一',
  INDEX `fk_user_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('c', 'admin', 'admin', 'd5b63d6021328feaa9b82a65eaefb012', b'0', 's0LoYwsVAnbQ8kSFSrF8', 'a', '2020-05-01 12:07:56.000000', '2020-05-03 17:13:29.376000');

SET FOREIGN_KEY_CHECKS = 1;
