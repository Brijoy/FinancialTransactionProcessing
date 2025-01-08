package com.td.notification_service.controller;

import com.td.notification_service.dto.MailNotificationRequest;
import com.td.notification_service.dto.NotificationDTO;
import com.td.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/notifications")

public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO notificationDTO) {

            NotificationDTO notificationDTO1 =  notificationService.createNotification(notificationDTO);
           // return ResponseEntity.ok().build();
        return new ResponseEntity<>(notificationDTO1, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        NotificationDTO notificationDTO = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notificationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable Long id,
                                                              @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO updatedNotificationDTO = notificationService.updateNotification(notificationDTO);
        return ResponseEntity.ok(updatedNotificationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/mail")
    public ResponseEntity<String> sendMailNotification(@RequestBody MailNotificationRequest request) {
        notificationService.sendNotification(request.getTo(), request.getSubject(), request.getBody());
        return ResponseEntity.ok("Mail notification sent successfully");
    }




}

