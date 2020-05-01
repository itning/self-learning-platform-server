package com.project.selflearningplatformserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.AttendanceDTO;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.Attendance;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.AttendanceMapper;
import com.project.selflearningplatformserver.mapper.UserMapper;
import com.project.selflearningplatformserver.service.AttendanceService;
import com.project.selflearningplatformserver.util.OrikaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author itning
 * @date 2020/5/1 22:38
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceMapper attendanceMapper, UserMapper userMapper) {
        this.attendanceMapper = attendanceMapper;
        this.userMapper = userMapper;
    }

    @Override
    public PageInfo<AttendanceDTO> getAllAttendances(int page, int size, String roleId) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(
                attendanceMapper.selectAllByUserRoleId(roleId)
                        .stream()
                        .map(attendance -> {
                            AttendanceDTO attendanceDTO = OrikaUtils.a2b(attendance, AttendanceDTO.class);
                            attendanceDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(attendance.getUserId()), UserDTO.class));
                            return attendanceDTO;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public AttendanceDTO newAttendance(LoginUser loginUser) {
        Date date = new Date();
        Attendance attendance = new Attendance();
        attendance.setId(UUID.randomUUID().toString());
        attendance.setUserId(loginUser.getId());
        attendance.setGmtCreate(date);
        attendance.setGmtModified(date);
        attendanceMapper.insert(attendance);
        AttendanceDTO attendanceDTO = OrikaUtils.a2b(attendance, AttendanceDTO.class);
        attendanceDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(attendance.getUserId()), UserDTO.class));
        return attendanceDTO;
    }

    @Override
    public AttendanceDTO newAttendance(String userId, LocalDateTime localDateTime) {
        Attendance attendance = new Attendance();
        attendance.setId(UUID.randomUUID().toString());
        attendance.setUserId(userId);
        attendance.setGmtCreate(new Date());
        attendance.setGmtModified(Date.from(localDateTime.atZone(ZoneId.of("Asia/Shanghai")).toInstant()));
        attendanceMapper.insert(attendance);
        AttendanceDTO attendanceDTO = OrikaUtils.a2b(attendance, AttendanceDTO.class);
        attendanceDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(attendance.getUserId()), UserDTO.class));
        return attendanceDTO;
    }

    @Override
    public void delAttendance(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("出勤ID为空");
        }
        if (attendanceMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("出勤ID不存在");
        }
        attendanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AttendanceDTO updateAttendance(String id, LocalDateTime localDateTime) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("出勤ID为空");
        }
        Attendance attendance = attendanceMapper.selectByPrimaryKey(id);
        if (Objects.isNull(attendance)) {
            throw new IdNotFoundException("出勤ID不存在");
        }
        Attendance att = new Attendance();
        att.setId(id);
        att.setGmtModified(Date.from(localDateTime.atZone(ZoneId.of("Asia/Shanghai")).toInstant()));
        attendanceMapper.updateByPrimaryKeySelective(att);
        AttendanceDTO attendanceDTO = OrikaUtils.a2b(att, AttendanceDTO.class);
        attendanceDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(attendance.getUserId()), UserDTO.class));
        return attendanceDTO;
    }
}
