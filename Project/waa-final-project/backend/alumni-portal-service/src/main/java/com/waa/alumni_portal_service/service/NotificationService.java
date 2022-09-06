package com.waa.alumni_portal_service.service;

import com.google.firebase.messaging.Notification;
import com.waa.alumni_portal_service.dto.NotificationInfoDTO;

public interface NotificationService {
    void saveUserToken(NotificationInfoDTO notificationInfo);

    void sendNotificationToUser(Notification notification, Integer userId);

}
