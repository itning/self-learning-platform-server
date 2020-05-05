package com.project.selflearningplatformserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author itning
 * @date 2020/5/3 19:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningContentDTO {
    private String id;

    private String subjectId;

    private String contentUri;

    private String aidUri;

    private Long aidSize;

    private String aidExtensionName;

    private String aidMime;

    private String extensionName;

    private Long size;

    private String mime;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    private String studentLearningId;

    private Date chooseDate;
}
