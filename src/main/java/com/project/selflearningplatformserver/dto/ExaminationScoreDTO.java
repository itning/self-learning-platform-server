package com.project.selflearningplatformserver.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author itning
 * @date 2020/5/1 21:33
 */
@Data
public class ExaminationScoreDTO {
    private String id;

    private String studentId;

    private String subject;

    private BigDecimal score;

    private String examId;

    private Date gmtCreate;

    private Date gmtModified;

    private UserDTO user;
}
