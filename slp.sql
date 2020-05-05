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

 Date: 06/05/2020 07:45:01
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
-- Records of examination
-- ----------------------------
INSERT INTO `examination` VALUES ('7f33cbb8-5db1-406c-9f38-8e3a777eef6e', '34a55218-8840-48d3-8f75-439114dcdb6b', '数学考试1', '2020-05-06 05:35:20.154000', '2020-05-06 05:35:20.154000');

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
-- Records of examination_score
-- ----------------------------
INSERT INTO `examination_score` VALUES ('37c215ff-f926-4094-807d-19d63bd42875', 'ea7d7135-22e8-41b5-91ff-f9d9cec69072', '数学', 100.00, '7f33cbb8-5db1-406c-9f38-8e3a777eef6e', '2020-05-06 05:40:47.198000', '2020-05-06 05:40:47.198000');
INSERT INTO `examination_score` VALUES ('65cbeb11-da1c-42f4-ae2c-f3cdbb3aec4f', 'ea7d7135-22e8-41b5-91ff-f9d9cec69072', '英语', 20.00, '7f33cbb8-5db1-406c-9f38-8e3a777eef6e', '2020-05-06 05:40:55.389000', '2020-05-06 05:40:55.389000');

-- ----------------------------
-- Table structure for learning_content
-- ----------------------------
DROP TABLE IF EXISTS `learning_content`;
CREATE TABLE `learning_content`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容ID',
  `subject_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容所属科目',
  `content_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习内容URI',
  `aid_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '其他内容文件URI',
  `aid_size` bigint(20) NOT NULL COMMENT '其他内容文件大小',
  `aid_extension_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '其他内容文件扩展名',
  `aid_mime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '其他内容文件MIME',
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
-- Records of learning_content
-- ----------------------------
INSERT INTO `learning_content` VALUES ('99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8', '4a59f60d-61ad-4fba-999f-0109a49f6cda', '99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8.mp4', '99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8.txt', 143, 'txt', 'text/plain', 'mp4', 7681416, 'video/mp4', '内容2', '2020-05-06 07:08:32.814000', '2020-05-06 07:12:09.712000');

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
INSERT INTO `log` VALUES ('0593807f-d77c-4f55-97f1-1049093c1b5d', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	修改学习内容	\n║调用参数：[LearningContent(id=99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8, subjectId=null, contentUri=null, aidUri=null, aidSize=null, aidExtensionName=null, aidMime=null, extensionName=null, size=null, mime=null, name=内容2, gmtCreate=null, gmtModified=null)]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 07:12:09.859000', '2020-05-06 07:12:09.859000');
INSERT INTO `log` VALUES ('08f0a5fc-7070-4915-985f-fc89c7bda724', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[a26f17ee-f8e9-43b4-adfc-15361551b37a]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:04.583000', '2020-05-04 13:06:04.583000');
INSERT INTO `log` VALUES ('0f35aa1c-0ae9-49f0-8fb3-bd8f9530a19b', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	教师删除信息	\n║调用参数：[cb5c4f02-f597-4d3d-a675-2f64b384f0b0]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:34:21.177000', '2020-05-06 06:34:21.177000');
INSERT INTO `log` VALUES ('17684010-d4d9-41d1-8e15-07635ab50a30', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增班级	\n║调用参数：[班级1]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=StudentClass(id=70e08398-304c-4b69-b88d-b9886a98b7d6, name=班级1, userId=34a55218-8840-48d3-8f75-439114dcdb6b, gmtCreate=Wed May 06 05:35:08 CST 2020, gmtModified=Wed May 06 05:35:08 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 05:35:09.059000', '2020-05-06 05:35:09.059000');
INSERT INTO `log` VALUES ('1d3b5ef6-83cf-44ee-b2fb-f0ca48dbfe00', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增考试分数	\n║调用参数：[ExaminationScore(id=65cbeb11-da1c-42f4-ae2c-f3cdbb3aec4f, studentId=ea7d7135-22e8-41b5-91ff-f9d9cec69072, subject=英语, score=20, examId=7f33cbb8-5db1-406c-9f38-8e3a777eef6e, gmtCreate=Wed May 06 05:40:55 CST 2020, gmtModified=Wed May 06 05:40:55 CST 2020)]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=ExaminationScoreDTO(id=65cbeb11-da1c-42f4-ae2c-f3cdbb3aec4f, studentId=ea7d7135-22e8-41b5-91ff-f9d9cec69072, subject=英语, score=20, examId=7f33cbb8-5db1-406c-9f38-8e3a777eef6e, gmtCreate=Wed May 06 05:40:55 CST 2020, gmtModified=Wed May 06 05:40:55 CST 2020, user=UserDTO(id=ea7d7135-22e8-41b5-91ff-f9d9cec69072, name=学生1, username=s1, freeze=false, roleId=c, gmtCreate=Wed May 06 05:35:42 CST 2020, gmtModified=Wed May 06 05:35:42 CST 2020)))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 05:40:55.558000', '2020-05-06 05:40:55.558000');
INSERT INTO `log` VALUES ('2239a182-7088-43ca-aec2-fc931b5840e0', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增学习内容	\n║调用参数：[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@3a3e4bd3][org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5bcdee20][a][4a59f60d-61ad-4fba-999f-0109a49f6cda]\n║返回参数：状态码：201 响应体：null\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:47:11.487000', '2020-05-06 06:47:11.487000');
INSERT INTO `log` VALUES ('227bd21b-d74c-4db2-800c-99a18bc29206', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[dd516c87-a215-4692-9133-229432465426]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:07.685000', '2020-05-04 13:06:07.685000');
INSERT INTO `log` VALUES ('24f2dddc-b053-479b-a615-b5ae536c593f', '╔═══════════════════════════════════\n║admin(ID:c)	新增教师	\n║调用参数：[t1][t1]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=UserDTO(id=34a55218-8840-48d3-8f75-439114dcdb6b, name=t1, username=t1, freeze=false, roleId=b, gmtCreate=Tue May 05 22:49:28 CST 2020, gmtModified=Tue May 05 22:49:28 CST 2020))\n╚═══════════════════════════════════', 'c', '2020-05-05 22:49:28.602000', '2020-05-05 22:49:28.602000');
INSERT INTO `log` VALUES ('281fa372-4f91-4e45-961b-3cc100ace3da', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增科目	\n║调用参数：[数学]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=Subject(id=4a59f60d-61ad-4fba-999f-0109a49f6cda, userId=34a55218-8840-48d3-8f75-439114dcdb6b, name=数学, gmtCreate=Tue May 05 22:50:06 CST 2020, gmtModified=Tue May 05 22:50:06 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-05 22:50:06.455000', '2020-05-05 22:50:06.455000');
INSERT INTO `log` VALUES ('4a7aa859-6162-47ab-94e7-a33374bc83c2', '╔═══════════════════════════════════\n║admin(ID:c)	删除用户信息	\n║调用参数：[58037417-5f8e-4008-b2d3-a3bd52e2af27]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-04 13:06:12.121000', '2020-05-04 13:06:12.121000');
INSERT INTO `log` VALUES ('65950d5e-fc05-4491-8b42-e58de87fb226', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	教师修改信息	\n║调用参数：[Teacher(id=cb5c4f02-f597-4d3d-a675-2f64b384f0b0, userId=null, attributeKey=性别, attributeValue=女, gmtCreate=null, gmtModified=Wed May 06 06:34:15 CST 2020)]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:34:15.647000', '2020-05-06 06:34:15.647000');
INSERT INTO `log` VALUES ('7409f205-0c6b-4780-86ea-2ff910c3a393', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[77e4a0c0-db55-4574-902c-0879448f1261]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:13.352000', '2020-05-03 22:17:13.352000');
INSERT INTO `log` VALUES ('7c78399c-a3f2-4931-9e9b-f1e29d4e9b1a', '╔═══════════════════════════════════\n║admin(ID:c)	新增教师	\n║调用参数：[teacher2][teacher2]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=UserDTO(id=dd516c87-a215-4692-9133-229432465426, name=teacher2, username=teacher2, freeze=false, roleId=b, gmtCreate=Mon May 04 11:10:20 CST 2020, gmtModified=Mon May 04 11:10:20 CST 2020))\n╚═══════════════════════════════════', 'c', '2020-05-04 11:10:20.586000', '2020-05-04 11:10:20.586000');
INSERT INTO `log` VALUES ('87b3c902-3dda-481c-9ce2-157282f9b09d', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增学习内容	\n║调用参数：[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5cc628e][org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@b0b9c47][a][4a59f60d-61ad-4fba-999f-0109a49f6cda]\n║返回参数：状态码：201 响应体：null\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:49:23.485000', '2020-05-06 06:49:23.485000');
INSERT INTO `log` VALUES ('a00e8882-a217-410d-9708-32328d711c07', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	教师新增信息	\n║调用参数：[年龄][23]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=Teacher(id=97c858e5-2268-4c91-9f68-be611b785367, userId=34a55218-8840-48d3-8f75-439114dcdb6b, attributeKey=年龄, attributeValue=23, gmtCreate=Wed May 06 06:34:47 CST 2020, gmtModified=Wed May 06 06:34:47 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:34:47.930000', '2020-05-06 06:34:47.930000');
INSERT INTO `log` VALUES ('ab4de70e-38a1-4f41-97fd-31d255c35d1f', '╔═══════════════════════════════════\n║学生1(ID:ea7d7135-22e8-41b5-91ff-f9d9cec69072)	学生选择学习内容	\n║调用参数：[99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=StudentLearning(id=173b2e88-1c64-46c8-ba3c-54cda3a1d66c, learningContentId=99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8, studentId=ea7d7135-22e8-41b5-91ff-f9d9cec69072, gmtCreate=Wed May 06 07:13:15 CST 2020, gmtModified=Wed May 06 07:13:15 CST 2020))\n╚═══════════════════════════════════', 'ea7d7135-22e8-41b5-91ff-f9d9cec69072', '2020-05-06 07:13:15.700000', '2020-05-06 07:13:15.700000');
INSERT INTO `log` VALUES ('bd21c11d-4c30-41eb-9943-a8eb2f85e677', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[7fbc73ce-c716-436f-aa0c-ac67e35c95ac]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:15.635000', '2020-05-03 22:17:15.635000');
INSERT INTO `log` VALUES ('bd9a631b-948c-4e79-a218-31f77a7f5f85', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	教师新增信息	\n║调用参数：[性别][男]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=Teacher(id=cb5c4f02-f597-4d3d-a675-2f64b384f0b0, userId=34a55218-8840-48d3-8f75-439114dcdb6b, attributeKey=性别, attributeValue=男, gmtCreate=Wed May 06 06:33:18 CST 2020, gmtModified=Wed May 06 06:33:18 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:33:18.605000', '2020-05-06 06:33:18.605000');
INSERT INTO `log` VALUES ('c08345bb-a3e1-4326-b9d4-2ace8b1c10a6', '╔═══════════════════════════════════\n║admin(ID:c)	删除公告	\n║调用参数：[0ecd02fc-c1c7-4afe-b440-598ed8dc097b]\n║返回参数：状态码：204 响应体：null\n╚═══════════════════════════════════', 'c', '2020-05-03 22:17:10.712000', '2020-05-03 22:17:10.712000');
INSERT INTO `log` VALUES ('c33e4a2c-9a86-4317-b0df-7cde9eb68733', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增学习内容	\n║调用参数：[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5bdfd8ba][org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5fd2b604][内容1][4a59f60d-61ad-4fba-999f-0109a49f6cda]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=LearningContent(id=99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8, subjectId=4a59f60d-61ad-4fba-999f-0109a49f6cda, contentUri=99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8.mp4, aidUri=99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8.txt, aidSize=143, aidExtensionName=txt, aidMime=text/plain, extensionName=mp4, size=7681416, mime=video/mp4, name=内容1, gmtCreate=Wed May 06 07:08:32 CST 2020, gmtModified=Wed May 06 07:08:32 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 07:08:33.072000', '2020-05-06 07:08:33.072000');
INSERT INTO `log` VALUES ('d5e61fba-296c-473d-8cee-e4ba9e2daea1', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增考试信息	\n║调用参数：[数学考试1]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=Examination(id=7f33cbb8-5db1-406c-9f38-8e3a777eef6e, userId=34a55218-8840-48d3-8f75-439114dcdb6b, name=数学考试1, gmtCreate=Wed May 06 05:35:20 CST 2020, gmtModified=Wed May 06 05:35:20 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 05:35:20.343000', '2020-05-06 05:35:20.343000');
INSERT INTO `log` VALUES ('da0b791a-3f66-4589-b627-a5cb14baac0d', '╔═══════════════════════════════════\n║admin(ID:c)	新增教师	\n║调用参数：[教师测试账户1][teacher]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=UserDTO(id=a26f17ee-f8e9-43b4-adfc-15361551b37a, name=教师测试账户1, username=teacher, freeze=false, roleId=b, gmtCreate=Sun May 03 22:18:19 CST 2020, gmtModified=Sun May 03 22:18:19 CST 2020))\n╚═══════════════════════════════════', 'c', '2020-05-03 22:18:20.035000', '2020-05-03 22:18:20.035000');
INSERT INTO `log` VALUES ('da53f5fe-af69-49f5-b5ff-e3d6c769934b', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	教师新增信息	\n║调用参数：[性别][男]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=Teacher(id=93827250-7724-40ed-92eb-df4ae6cbe840, userId=34a55218-8840-48d3-8f75-439114dcdb6b, attributeKey=性别, attributeValue=男, gmtCreate=Wed May 06 06:34:39 CST 2020, gmtModified=Wed May 06 06:34:39 CST 2020))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 06:34:39.722000', '2020-05-06 06:34:39.722000');
INSERT INTO `log` VALUES ('f88d9a86-f610-4d10-8c14-ddec14ce6068', '╔═══════════════════════════════════\n║t1(ID:34a55218-8840-48d3-8f75-439114dcdb6b)	新增考试分数	\n║调用参数：[ExaminationScore(id=37c215ff-f926-4094-807d-19d63bd42875, studentId=ea7d7135-22e8-41b5-91ff-f9d9cec69072, subject=数学, score=100, examId=7f33cbb8-5db1-406c-9f38-8e3a777eef6e, gmtCreate=Wed May 06 05:40:47 CST 2020, gmtModified=Wed May 06 05:40:47 CST 2020)]\n║返回参数：状态码：201 响应体：RestModel(code=201, msg=创建成功, data=ExaminationScoreDTO(id=37c215ff-f926-4094-807d-19d63bd42875, studentId=ea7d7135-22e8-41b5-91ff-f9d9cec69072, subject=数学, score=100, examId=7f33cbb8-5db1-406c-9f38-8e3a777eef6e, gmtCreate=Wed May 06 05:40:47 CST 2020, gmtModified=Wed May 06 05:40:47 CST 2020, user=UserDTO(id=ea7d7135-22e8-41b5-91ff-f9d9cec69072, name=学生1, username=s1, freeze=false, roleId=c, gmtCreate=Wed May 06 05:35:42 CST 2020, gmtModified=Wed May 06 05:35:42 CST 2020)))\n╚═══════════════════════════════════', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 05:40:47.323000', '2020-05-06 05:40:47.323000');

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
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('ea7d7135-22e8-41b5-91ff-f9d9cec69072', '70e08398-304c-4b69-b88d-b9886a98b7d6', '2020-05-06 05:35:42.425000', '2020-05-06 05:35:42.425000');

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
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES ('70e08398-304c-4b69-b88d-b9886a98b7d6', '班级1', '34a55218-8840-48d3-8f75-439114dcdb6b', '2020-05-06 05:35:08.849000', '2020-05-06 05:35:08.849000');

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
-- Records of student_learning
-- ----------------------------
INSERT INTO `student_learning` VALUES ('173b2e88-1c64-46c8-ba3c-54cda3a1d66c', '99e83af5-ea7b-4cfa-8de7-2a1e15b2fde8', 'ea7d7135-22e8-41b5-91ff-f9d9cec69072', '2020-05-06 07:13:15.543000', '2020-05-06 07:13:15.543000');

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
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('4a59f60d-61ad-4fba-999f-0109a49f6cda', '34a55218-8840-48d3-8f75-439114dcdb6b', '数学', '2020-05-05 22:50:06.328000', '2020-05-05 22:50:06.328000');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师信息ID',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师ID',
  `attribute_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '信息键',
  `attribute_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '信息值',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_teacher_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_teacher_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('93827250-7724-40ed-92eb-df4ae6cbe840', '34a55218-8840-48d3-8f75-439114dcdb6b', '性别', '男', '2020-05-06 06:34:39.585000', '2020-05-06 06:34:39.585000');
INSERT INTO `teacher` VALUES ('97c858e5-2268-4c91-9f68-be611b785367', '34a55218-8840-48d3-8f75-439114dcdb6b', '年龄', '23', '2020-05-06 06:34:47.784000', '2020-05-06 06:34:47.784000');

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
INSERT INTO `user` VALUES ('34a55218-8840-48d3-8f75-439114dcdb6b', 't1', 't1', '5bd73be54356c4c0238ff1616d01b0e8', b'0', 'WYSgVbqyE1cGD8RTINLG', 'b', '2020-05-05 22:49:28.412000', '2020-05-05 22:49:28.412000');
INSERT INTO `user` VALUES ('c', 'admin', 'admin', 'd5b63d6021328feaa9b82a65eaefb012', b'0', 's0LoYwsVAnbQ8kSFSrF8', 'a', '2020-05-01 12:07:56.000000', '2020-05-03 17:13:29.376000');
INSERT INTO `user` VALUES ('ea7d7135-22e8-41b5-91ff-f9d9cec69072', '学生1', 's1', '645dd19986c45165f62e7a8a7347c7ea', b'0', 'qSpitCxc0uUvu8OebFbR', 'c', '2020-05-06 05:35:42.425000', '2020-05-06 05:35:42.425000');

SET FOREIGN_KEY_CHECKS = 1;
