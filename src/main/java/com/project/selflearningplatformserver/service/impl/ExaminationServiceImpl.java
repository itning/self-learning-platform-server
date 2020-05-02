package com.project.selflearningplatformserver.service.impl;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.entity.Examination;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.ExaminationMapper;
import com.project.selflearningplatformserver.service.ExaminationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 21:13
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ExaminationServiceImpl implements ExaminationService {
    private final ExaminationMapper examinationMapper;

    @Autowired
    public ExaminationServiceImpl(ExaminationMapper examinationMapper) {
        this.examinationMapper = examinationMapper;
    }

    @Override
    public List<Examination> getAllExamination(LoginUser loginUser) {
        return examinationMapper.selectAllByUserId(loginUser.getId());
    }

    @Override
    public void delExamination(LoginUser loginUser, String id) {
        Examination examination = examinationMapper.selectByPrimaryKey(id);
        if (Objects.isNull(examination)) {
            throw new IdNotFoundException("考试信息ID不存在");
        }
        if (!examination.getUserId().equals(loginUser.getId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        examinationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Examination newExamination(LoginUser loginUser, String name) {
        if (StringUtils.isBlank(name)) {
            throw new NullFiledException("考试信息名为空");
        }
        Date date = new Date();
        Examination examination = new Examination();
        examination.setId(UUID.randomUUID().toString());
        examination.setUserId(loginUser.getId());
        examination.setName(name);
        examination.setGmtCreate(date);
        examination.setGmtModified(date);
        examinationMapper.insert(examination);
        return examination;
    }

    @Override
    public Examination updateExamination(LoginUser loginUser, Examination examination) {
        if (StringUtils.isAnyBlank(examination.getId(), examination.getName())) {
            throw new NullFiledException("考试信息为空");
        }
        Examination saved = examinationMapper.selectByPrimaryKey(examination.getId());
        if (!saved.getUserId().equals(loginUser.getId())) {
            throw new SecurityServerException("更新失败", HttpStatus.FORBIDDEN);
        }
        examination.setGmtCreate(null);
        examination.setGmtModified(new Date());
        examination.setUserId(null);
        examinationMapper.updateByPrimaryKeySelective(examination);
        return examination;
    }
}
