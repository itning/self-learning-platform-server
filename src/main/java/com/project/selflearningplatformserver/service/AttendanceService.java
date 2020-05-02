package com.project.selflearningplatformserver.service;

import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.AttendanceDTO;
import com.project.selflearningplatformserver.dto.LoginUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 出勤
 *
 * @author itning
 * @date 2020/5/1 22:30
 */
public interface AttendanceService {
    /**
     * 管理员获取出勤记录
     *
     * @param roleId 角色
     * @return 出勤记录
     */
    List<AttendanceDTO> getAllAttendances(String roleId);

    /**
     * 教师/学生新增出勤记录
     *
     * @param loginUser 登录用户
     * @return 出勤记录
     */
    AttendanceDTO newAttendance(LoginUser loginUser);

    /**
     * 管理员新增出勤记录
     *
     * @param userId        用户ID
     * @param localDateTime 时间
     * @return 出勤记录
     */
    AttendanceDTO newAttendance(String userId, LocalDateTime localDateTime);

    /**
     * 删除出勤记录
     *
     * @param id 出勤记录ID
     */
    void delAttendance(String id);

    /**
     * 修改出勤记录
     *
     * @param id            记录ID
     * @param localDateTime 新时间
     * @return 出勤记录
     */
    AttendanceDTO updateAttendance(String id, LocalDateTime localDateTime);
}
