package com.td.notification_serivce.controller;

import com.td.notification_serivce.dto.NotificationDTO;
import com.td.notification_serivce.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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

}

