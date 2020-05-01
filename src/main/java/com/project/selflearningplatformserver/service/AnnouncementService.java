package com.project.selflearningplatformserver.service;

import com.project.selflearningplatformserver.entity.Announcement;

import java.util.List;

/**
 * 公告服务
 *
 * @author itning
 * @date 2020/5/1 18:52
 */
public interface AnnouncementService {
    /**
     * 获取所有公告
     *
     * @return 公告集合
     */
    List<Announcement> getAll();

    /**
     * 删除公告
     *
     * @param id 公告ID
     */
    void del(String id);

    /**
     * 更新公告
     *
     * @param id      公告ID
     * @param content 公告内容
     * @return 更新的公告
     */
    Announcement update(String id, String content);

    /**
     * 新增公告
     *
     * @param content 内容
     * @return 新增的公告
     */
    Announcement newAnnouncement(String content);
}
