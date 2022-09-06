package com.waa.alumni_portal_service.controller;

import com.google.firebase.messaging.Notification;
import com.waa.alumni_portal_service.dto.NotificationInfoDTO;
import com.waa.alumni_portal_service.notification.FCMService;
import com.waa.alumni_portal_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notificationInfo")
@CrossOrigin
public class NotificationInfoController {
    private final NotificationService notificationService;

    @GetMapping("/test/{userId}")
    public void sendNotification(@PathVariable Integer userId) {
        Notification notification = new Notification("Test notification", "Notification content");
        notificationService.sendNotificationToUser(notification, userId);
    }

    @PostMapping("/registerToken")
    public void registerToken(@RequestBody NotificationInfoDTO notificationInfoDTO){
        notificationService.saveUserToken(notificationInfoDTO);
    }
}
