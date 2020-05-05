package com.project.selflearningplatformserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 考试分数（带考试信息）
 *
 * @author itning
 * @date 2020/5/6 5:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationScoreWithExamName {
    private String id;

    private String studentId;

    private String subject;

    private BigDecimal score;

    private String examId;

    private Date gmtCreate;

    private Date gmtModified;

    private String name;
}
