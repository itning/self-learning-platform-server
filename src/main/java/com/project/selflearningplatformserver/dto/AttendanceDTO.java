package com.project.selflearningplatformserver.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/1 22:40
 */
@Data
public class AttendanceDTO {
    private String id;

    private String userId;

    private Date gmtCreate;

    private Date gmtModified;

    private UserDTO user;
}
