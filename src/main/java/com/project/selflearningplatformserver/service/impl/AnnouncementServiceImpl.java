package com.project.selflearningplatformserver.service.impl;

import java.util.Date;

import com.project.selflearningplatformserver.entity.Announcement;
import com.project.selflearningplatformserver.exception.IdNotFoundException;
import com.project.selflearningplatformserver.exception.NullFiledException;
import com.project.selflearningplatformserver.mapper.AnnouncementMapper;
import com.project.selflearningplatformserver.service.AnnouncementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author itning
 * @date 2020/5/1 18:57
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementMapper announcementMapper;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public List<Announcement> getAll() {
        return announcementMapper.selectAll();
    }

    @Override
    public void del(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NullFiledException("公告ID为空");
        }
        if (announcementMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("公告ID不存在");
        }
        announcementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Announcement update(String id, String content) {
        if (StringUtils.isBlank(content)) {
            throw new NullFiledException("公告为空");
        }
        if (announcementMapper.countByPrimaryKey(id) == 0L) {
            throw new IdNotFoundException("公告ID不存在");
        }
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setGmtModified(new Date());
        announcement.setContent(content);
        announcementMapper.updateByPrimaryKeySelective(announcement);
        return announcement;
    }

    @Override
    public Announcement newAnnouncement(String content) {
        if (StringUtils.isBlank(content)) {
            throw new NullFiledException("公告为空");
        }
        Date date = new Date();
        Announcement announcement = new Announcement();
        announcement.setId(UUID.randomUUID().toString());
        announcement.setContent(content);
        announcement.setGmtCreate(date);
        announcement.setGmtModified(date);
        announcementMapper.insert(announcement);
        return announcement;
    }
}
