package com.project.selflearningplatformserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/2 20:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassDTO {
    private String id;

    private String name;

    private String userId;

    private Date gmtCreate;

    private Date gmtModified;

    private String teacherName;
}
