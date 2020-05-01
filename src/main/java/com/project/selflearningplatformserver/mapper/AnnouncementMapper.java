package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Announcement;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(String id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
}