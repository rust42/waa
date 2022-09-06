package com.waa.alumni_portal_service.notification;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    public void sendNotification(Notification notification, String token) {
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();
        ApiFuture<String> future = FirebaseMessaging.getInstance().sendAsync(message);
        ApiFutures.addCallback(
            future,
            new ApiFutureCallback<String>() {
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
                @Override
                public void onSuccess(String response) {
                    System.out.println("Send notification success: " + response);
                }
            },
            MoreExecutors.directExecutor());
    }
}
