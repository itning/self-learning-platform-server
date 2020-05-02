package com.project.selflearningplatformserver.controller;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.dto.RestModel;
import com.project.selflearningplatformserver.entity.Announcement;
import com.project.selflearningplatformserver.log.Log;
import com.project.selflearningplatformserver.security.MustAdminLogin;
import com.project.selflearningplatformserver.security.MustLogin;
import com.project.selflearningplatformserver.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 公告
 *
 * @author itning
 * @date 2020/5/1 19:04
 */
@RestController
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    /**
     * 获取公告信息
     *
     * @param loginUser 登录用户
     * @return ResponseEntity
     */
    @GetMapping("/announcements")
    public ResponseEntity<?> getAllAnnouncement(@MustLogin LoginUser loginUser) {
        return RestModel.ok(announcementService.getAll());
    }

    /**
     * 管理员新增公告
     *
     * @param loginUser 登录用户
     * @param content   内容
     * @return ResponseEntity
     */
    @Log("新增公告")
    @PostMapping("/announcement")
    public ResponseEntity<?> newAnnouncement(@MustAdminLogin LoginUser loginUser,
                                             @RequestParam String content) {
        return RestModel.created(announcementService.newAnnouncement(content));
    }

    /**
     * 管理员删除公告
     *
     * @param loginUser 登录用户
     * @param id        公告ID
     * @return ResponseEntity
     */
    @Log("删除公告")
    @DeleteMapping("/announcement/{id}")
    public ResponseEntity<?> delAnnouncement(@MustAdminLogin LoginUser loginUser,
                                             @PathVariable String id) {
        announcementService.del(id);
        return RestModel.noContent();
    }

    /**
     * 管理员修改公告
     *
     * @param loginUser    登录用户
     * @param announcement 修改的公告信息
     * @return ResponseEntity
     */
    @Log("修改公告")
    @PatchMapping("/announcement")
    public ResponseEntity<?> updateAnnouncement(@MustAdminLogin LoginUser loginUser,
                                                @RequestBody Announcement announcement) {
        announcementService.update(announcement.getId(), announcement.getContent());
        return RestModel.noContent();
    }
}
