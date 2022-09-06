package com.waa.alumni_portal_service.service.impl;

import com.google.firebase.messaging.Notification;
import com.waa.alumni_portal_service.dto.NotificationInfoDTO;
import com.waa.alumni_portal_service.entity.NotificationInfo;
import com.waa.alumni_portal_service.notification.FCMService;
import com.waa.alumni_portal_service.repository.NotificationInfoRepository;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.NotificationService;
import com.waa.alumni_portal_service.util.UserDetailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final FCMService fcmService;
    private final ModelMapper modelMapper;
    private final NotificationInfoRepository notiRepository;
    private final UserDetailUtil userDetailUtil;

    @Override
    synchronized public void saveUserToken(NotificationInfoDTO notificationInfoDTO) {
        notificationInfoDTO.setUser(userDetailUtil.getCurrentUser());
        Optional<NotificationInfo> existedNotificationInfo = notiRepository.findByUserId(notificationInfoDTO.getUser().getId());
        NotificationInfo notificationInfo = null;
        if(existedNotificationInfo.isPresent()){
            notificationInfo = existedNotificationInfo.get();
            notificationInfo.setToken(notificationInfo.getToken());
        }else{
            notificationInfo = modelMapper.map(notificationInfoDTO, NotificationInfo.class);
        }
        notiRepository.save(notificationInfo);
    }

    @Override
    public void sendNotificationToUser(Notification notification, Integer userId) {
        notiRepository.findByUserId(userId).map( notificationInfo -> {
            fcmService.sendNotification(notification, notificationInfo.getToken());
            return notificationInfo;
        });
    }
}
