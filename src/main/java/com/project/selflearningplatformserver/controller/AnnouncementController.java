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

    @GetMapping("/announcements")
    public ResponseEntity<?> getAllAnnouncement(@MustLogin LoginUser loginUser) {
        return RestModel.ok(announcementService.getAll());
    }

    @Log("新增公告")
    @PostMapping("/announcement")
    public ResponseEntity<?> newAnnouncement(@MustAdminLogin LoginUser loginUser,
                                             @RequestParam String content) {
        return RestModel.created(announcementService.newAnnouncement(content));
    }

    @Log("删除公告")
    @DeleteMapping("/announcement/{id}")
    public ResponseEntity<?> delAnnouncement(@MustAdminLogin LoginUser loginUser,
                                             @PathVariable String id) {
        announcementService.del(id);
        return RestModel.noContent();
    }

    @Log("修改公告")
    @PatchMapping("/announcement")
    public ResponseEntity<?> updateAnnouncement(@MustAdminLogin LoginUser loginUser,
                                                @RequestBody Announcement announcement) {
        announcementService.update(announcement.getId(), announcement.getContent());
        return RestModel.noContent();
    }
}
