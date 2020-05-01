package com.project.selflearningplatformserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationScore {
    private String id;

    private String studentId;

    private String subject;

    private BigDecimal score;

    private String examId;

    private Date gmtCreate;

    private Date gmtModified;
}