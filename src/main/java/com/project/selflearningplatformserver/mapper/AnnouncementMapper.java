package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.Announcement;

import java.util.List;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(String id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    List<Announcement> selectAll();

    long countByPrimaryKey(String id);
}