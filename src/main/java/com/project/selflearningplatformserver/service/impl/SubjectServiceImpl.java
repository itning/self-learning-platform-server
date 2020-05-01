package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Subject;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.SubjectMapper;
import com.project.selflearningplatformserver.service.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 19:38
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectServiceImpl(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<Subject> getAllSubject(LoginUser loginUser) {
        return subjectMapper.selectByUserId(loginUser.getId());
    }

    @Override
    public void delSubject(LoginUser loginUser, String subjectId) {
        if (StringUtils.isBlank(subjectId)) {
            throw new NullFiledException("科目ID为空");
        }
        if (subjectMapper.countByPrimaryKey(subjectId) == 0L) {
            throw new IdNotFoundException("科目ID不存在");
        }
        subjectMapper.deleteByPrimaryKey(subjectId);
    }

    @Override
    public Subject newSubject(LoginUser loginUser, String name) {
        if (StringUtils.isBlank(name)) {
            throw new NullFiledException("科目名为空");
        }
        Date date = new Date();
        Subject subject = new Subject();
        subject.setId(UUID.randomUUID().toString());
        subject.setUserId(loginUser.getId());
        subject.setName(name);
        subject.setGmtCreate(date);
        subject.setGmtModified(date);
        subjectMapper.insert(subject);
        return subject;
    }

    @Override
    public Subject updateSubject(LoginUser loginUser, Subject subject) {
        if (StringUtils.isBlank(subject.getId())) {
            throw new NullFiledException("科目ID为空");
        }
        if (subjectMapper.countByPrimaryKey(subject.getId()) == 0L) {
            throw new IdNotFoundException("科目ID不存在");
        }
        if (StringUtils.isBlank(subject.getName())) {
            throw new NullFiledException("科目名为空");
        }
        subject.setGmtCreate(null);
        subject.setGmtModified(null);
        subject.setUserId(null);
        subjectMapper.updateByPrimaryKeySelective(subject);
        return subject;
    }
}
