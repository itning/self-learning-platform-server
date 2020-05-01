package com.project.selflearningplatformserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.LearningContent;
import com.project.selflearningplatformserver.entity.Subject;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.LearningContentMapper;
import com.project.selflearningplatformserver.mapper.SubjectMapper;
import com.project.selflearningplatformserver.service.LearningContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author itning
 * @date 2020/5/1 20:46
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LearningContentServiceImpl implements LearningContentService {
    private final LearningContentMapper learningContentMapper;
    private final SubjectMapper subjectMapper;

    @Autowired
    public LearningContentServiceImpl(LearningContentMapper learningContentMapper, SubjectMapper subjectMapper) {
        this.learningContentMapper = learningContentMapper;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public PageInfo<LearningContent> getAllBySubjectId(String subjectId, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(learningContentMapper.selectAllBySubjectId(subjectId));
    }

    @Override
    public void delLearningContent(LoginUser loginUser, String id) {
        LearningContent learningContent = learningContentMapper.selectByPrimaryKey(id);
        if (Objects.isNull(learningContent)) {
            throw new IdNotFoundException("学习内容ID不存在");
        }
        Subject subject = subjectMapper.selectByPrimaryKey(learningContent.getSubjectId());
        if (!loginUser.getId().equals(subject.getId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        learningContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public LearningContent newLearningContent(LoginUser loginUser, MultipartFile file, String subjectId) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        //TODO 新增
        return null;
    }

    @Override
    public LearningContent updateLearningContent(LoginUser loginUser, MultipartFile file, LearningContent learningContent) {
        //TODO 更新
        return null;
    }
}
