package com.td.notification_service.controller;

import com.td.notification_service.dto.NotificationTemplateDTO;
import com.td.notification_service.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-templates")

public class NotificationTemplateController {

    @Autowired
    private NotificationTemplateService notificationTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplateDTO> getNotificationTemplateById(@PathVariable Long id) {
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateService.getNotificationTemplateById(id);
        return ResponseEntity.ok(notificationTemplateDTO);
    }

    @PostMapping
    public ResponseEntity<NotificationTemplateDTO> createNotificationTemplate(@RequestBody NotificationTemplateDTO notificationTemplateDTO) {
        NotificationTemplateDTO createdNotificationTemplateDTO = notificationTemplateService.createNotificationTemplate(notificationTemplateDTO);
        return ResponseEntity.ok(createdNotificationTemplateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplateDTO> updateNotificationTemplate(@PathVariable Long id,
                                                                              @RequestBody NotificationTemplateDTO notificationTemplateDTO) {
        NotificationTemplateDTO updatedNotificationTemplateDTO = notificationTemplateService.updateNotificationTemplate(notificationTemplateDTO);
        return ResponseEntity.ok(updatedNotificationTemplateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationTemplate(@PathVariable Long id) {
        notificationTemplateService.deleteNotificationTemplate(id);
        return ResponseEntity.noContent().build();
    }

}
