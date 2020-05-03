package com.project.selflearningplatformserver.dto;

import com.project.selflearningplatformserver.entity.StudentWork;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/3 14:04
 */
@Data
@NoArgsConstructor
public class StudentLearningDTO {
    private String id;

    private String learningContentId;

    private String studentId;

    private Date gmtCreate;

    private Date gmtModified;

    private String studentName;

    private StudentWork studentWork;

    public StudentLearningDTO(String id, String learningContentId, String studentId, Date gmtCreate, Date gmtModified, String studentName) {
        this.id = id;
        this.learningContentId = learningContentId;
        this.studentId = studentId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.studentName = studentName;
    }
}
