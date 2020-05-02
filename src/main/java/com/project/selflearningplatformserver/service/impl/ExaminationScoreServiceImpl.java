package com.project.selflearningplatformserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.selflearningplatformserver.dto.ExaminationScoreDTO;
import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.UserDTO;
import com.project.selflearningplatformserver.entity.Examination;
import com.project.selflearningplatformserver.entity.ExaminationScore;
import com.project.selflearningplatformserver.entity.Student;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.exception.SecurityServerException;
import com.project.selflearningplatformserver.mapper.*;
import com.project.selflearningplatformserver.service.ExaminationScoreService;
import com.project.selflearningplatformserver.util.OrikaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author itning
 * @date 2020/5/1 21:41
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ExaminationScoreServiceImpl implements ExaminationScoreService {
    private final ExaminationScoreMapper examinationScoreMapper;
    private final ExaminationMapper examinationMapper;
    private final UserMapper userMapper;
    private final StudentClassMapper studentClassMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public ExaminationScoreServiceImpl(ExaminationScoreMapper examinationScoreMapper, ExaminationMapper examinationMapper, UserMapper userMapper, StudentClassMapper studentClassMapper, StudentMapper studentMapper) {
        this.examinationScoreMapper = examinationScoreMapper;
        this.examinationMapper = examinationMapper;
        this.userMapper = userMapper;
        this.studentClassMapper = studentClassMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public PageInfo<ExaminationScoreDTO> getAllByExaminationId(LoginUser loginUser, String examinationId, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(
                examinationScoreMapper.selectAllByExaminationId(examinationId)
                        .stream()
                        .map(examinationScore -> {
                            ExaminationScoreDTO examinationScoreDTO = OrikaUtils.a2b(examinationScore, ExaminationScoreDTO.class);
                            examinationScoreDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(examinationScore.getStudentId()), UserDTO.class));
                            return examinationScoreDTO;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void del(LoginUser loginUser, String id) {
        ExaminationScore examinationScore = examinationScoreMapper.selectByPrimaryKey(id);
        if (Objects.isNull(examinationScore)) {
            throw new IdNotFoundException("学生成绩ID没有找到");
        }
        Examination examination = examinationMapper.selectByPrimaryKey(examinationScore.getExamId());
        if (!examination.getUserId().equals(loginUser.getId())) {
            throw new SecurityServerException("删除失败", HttpStatus.FORBIDDEN);
        }
        examinationScoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ExaminationScoreDTO newExaminationScore(LoginUser loginUser, ExaminationScore examinationScore) {
        if (StringUtils.isAnyBlank(examinationScore.getExamId(), examinationScore.getStudentId(), examinationScore.getSubject())
                || Objects.isNull(examinationScore.getScore())) {
            throw new NullFiledException("参数为空");
        }
        Examination examination = examinationMapper.selectByPrimaryKey(examinationScore.getExamId());
        if (Objects.isNull(examination)) {
            throw new NullFiledException("考试信息ID不存在");
        }
        Student student = studentMapper.selectByPrimaryKey(examinationScore.getStudentId());

        // 查找该教师的所有班级是否有要添加分数的学生所在的班级
        // 横向越权检查
        if (studentClassMapper.selectByUserId(loginUser.getId()).stream().noneMatch(studentClass -> studentClass.getId().equals(student.getStudentClassId()))
                || !examination.getUserId().equals(loginUser.getId())) {
            throw new SecurityServerException("新增失败", HttpStatus.FORBIDDEN);
        }
        Date date = new Date();
        examinationScore.setId(UUID.randomUUID().toString());
        examinationScore.setGmtCreate(date);
        examinationScore.setGmtModified(date);
        examinationScoreMapper.insert(examinationScore);
        ExaminationScoreDTO examinationScoreDTO = OrikaUtils.a2b(examinationScore, ExaminationScoreDTO.class);
        examinationScoreDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(student.getUserId()), UserDTO.class));
        return examinationScoreDTO;
    }

    @Override
    public ExaminationScoreDTO updateExaminationScore(LoginUser loginUser, ExaminationScore examinationScore) {
        if (StringUtils.isAnyBlank(examinationScore.getId())) {
            throw new NullFiledException("学生成绩ID为空");
        }
        ExaminationScore score = examinationScoreMapper.selectByPrimaryKey(examinationScore.getId());
        Examination examination = examinationMapper.selectByPrimaryKey(score.getExamId());
        if (!loginUser.getId().equals(examination.getUserId())) {
            throw new SecurityServerException("修改失败", HttpStatus.FORBIDDEN);
        }
        examinationScore.setGmtModified(new Date());
        examinationScore.setGmtCreate(null);
        examinationScoreMapper.updateByPrimaryKeySelective(examinationScore);
        ExaminationScoreDTO examinationScoreDTO = OrikaUtils.a2b(examinationScore, ExaminationScoreDTO.class);
        examinationScoreDTO.setUser(OrikaUtils.a2b(userMapper.selectByPrimaryKey(score.getStudentId()), UserDTO.class));
        return examinationScoreDTO;
    }
}
