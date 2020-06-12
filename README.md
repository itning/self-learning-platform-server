# 自学平台系统

[![GitHub stars](https://img.shields.io/github/stars/itning/self-learning-platform-server.svg?style=social&label=Stars)](https://github.com/itning/self-learning-platform-server/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/itning/self-learning-platform-server.svg?style=social&label=Fork)](https://github.com/itning/self-learning-platform-server/network/members)
[![GitHub watchers](https://img.shields.io/github/watchers/itning/self-learning-platform-server.svg?style=social&label=Watch)](https://github.com/itning/self-learning-platform-server/watchers)
[![GitHub followers](https://img.shields.io/github/followers/itning.svg?style=social&label=Follow)](https://github.com/itning?tab=followers)

[![GitHub issues](https://img.shields.io/github/issues/itning/self-learning-platform-server.svg)](https://github.com/itning/self-learning-platform-server/issues)
[![GitHub license](https://img.shields.io/github/license/itning/self-learning-platform-server.svg)](https://github.com/itning/self-learning-platform-server/blob/master/LICENSE)
[![GitHub last commit](https://img.shields.io/github/last-commit/itning/self-learning-platform-server.svg)](https://github.com/itning/self-learning-platform-server/commits)
[![GitHub release](https://img.shields.io/github/release/itning/self-learning-platform-server.svg)](https://github.com/itning/self-learning-platform-server/releases)
[![GitHub repo size in bytes](https://img.shields.io/github/repo-size/itning/self-learning-platform-server.svg)](https://github.com/itning/self-learning-platform-server)
[![HitCount](http://hits.dwyl.io/itning/self-learning-platform-server.svg)](http://hits.dwyl.io/itning/self-learning-platform-server)
[![language](https://img.shields.io/badge/language-JAVA-green.svg)](https://github.com/itning/self-learning-platform-server)

**前后端分离项目**，前端地址：[https://github.com/itning/self-learning-platform-client](https://github.com/itning/self-learning-platform-client)

## 功能大纲

1. 用户管理模块分为三个小模块:

- [x] （1）用户注册:用户注册个人信息，填写信息资料并提交后，可以登陆该系统

- [x] （2）用户信息修改:对系统中已经注册完毕的用户信息，进行修改操作

- [x] （3）用户信息删除:对系统中已经注册完毕的用户信息，进行删除操作
  

2. 系统设置模块分为二个小模块:

- [x] （1）权限设置:设定权限，不同权限具有不同操作功能

- [x]  （2）系统操作日志:人员登陆后，对系统进行了何种的功能操作生成日志。
3. 教师管理模块分为三个小模块:

- [x] （1）科目分类:对科目分类操作，添加科目，删除科目，修改科目，查询科目。

- [x] （2）教师信息:对教师信息操作，添加教师信息，修改教师信息，删除教师信息，查询教师信息。

- [x]   （3）班级管辖模块:老师在平台上对班级的管辖，添加班级，修改班级，删除班级，查询班级。
4. 学考模块分为四个小模块:

- [x] （1）学习内容种类:学生选择需要学习的内容种类，添加学习内容，修改学习内容，删除学习内容，查询学习内容。

- [x] （2）学生名单:考试的学生名单添加，修改学生名单，删除学生名单，查询学生名单。

- [x] （3）考试成绩:老师对学生的成绩登记，添加学生成绩，修改学生成绩，删除学生成绩，查询学生成绩。

- [x]   （4）教师建议:教师对学生提出建议，添加建议，修改建议，删除建议，查询建议。
5. 出勤模块分为三个小模块:

- [x] （1）教师出勤记录:添加教师出勤，修改教师出勤，删除教师出勤，查询教师出勤。

- [x] （2）学生出勤记录:添加学生出勤，修改学生出勤，删除学生出勤，查询学生出勤。

- [x]   （3）考试成绩:老师对学生的成绩登记，添加学生成绩，修改学生成绩，删除学生成绩，查询学生成绩
6. 公告模块分为四个小模块:

- [x] （1）添加公告信息:在系统中添加公告信息，学生和老师可以查看。

- [x] （2）删除公告信息:对系统中存在的公告信息删除。

- [x] （3）修改公告信息:对系统中存在的公告信息修改。

- [x]  （4）查询公告信息:对系统中存在的公告信息查询。
7. 安全管理分为三个小模块:

- [x] （1）账号冻结:对不明身份的账号进行冻结。

- [x] （2）账号解冻:对系统中冻结的账号解冻。

- [x] （3）密码保护:为密码加入md5加密状态。
---
- [x] 1.管理员或教师上传科目视频及ppt。
- [x] 2.将管理员添加的科目与教师和学生关联。
- [x] 3.学生登陆后可以观看视频，上传文件。
- [x] 4.学生用户登录以后不要与管理员登陆功能相同
- [x] 5.学生界面能打卡，管理端能查看打卡
- [x] 6.老师能查看到学生上传的文档进行评分，再给建议
---
- [x] 1.老师上传考试分数后，学生可查看自己的考试成绩。
- [x] 2.管理员为教师注册账号后，教师可添加、修改自己的信息（如性别、年龄、学历、电话、邮箱）
- [x] 3.学习内容管理增加上传文件（任意）学生可以下载

## 运行环境
- JDK 11
- MySQL 8
- Vue.JS

## 截图

![a](https://raw.githubusercontent.com/itning/self-learning-platform-server/master/pic/a.png)

![b](https://raw.githubusercontent.com/itning/self-learning-platform-server/master/pic/b.png)

![c](https://raw.githubusercontent.com/itning/self-learning-platform-server/master/pic/c.png)

![d](https://raw.githubusercontent.com/itning/self-learning-platform-server/master/pic/d.png)

![e](https://raw.githubusercontent.com/itning/self-learning-platform-server/master/pic/e.png)
