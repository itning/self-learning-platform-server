package com.project.selflearningplatformserver.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/1 15:04
 */
@Data
public class UserDTO {
    private String id;

    private String name;

    private String username;

    private Boolean freeze;

    private String roleId;

    private Date gmtCreate;

    private Date gmtModified;
}
