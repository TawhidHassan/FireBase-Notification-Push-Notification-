package com.example.firebasenotificationpushnotification.service;

import androidx.annotation.NonNull;

import com.example.firebasenotificationpushnotification.model.Notification;
import com.example.firebasenotificationpushnotification.utils.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //for forground notification======================

//        String body= remoteMessage.getNotification().getBody();
//        String title= remoteMessage.getNotification().getTitle();
//        String image= remoteMessage.getNotification().getImageUrl().toString();
//        String clickAction = remoteMessage.getNotification().getClickAction();
//
//        String key1 = remoteMessage.getData().get("key1");
//        String key2 = remoteMessage.getData().get("key2");
//        String text = remoteMessage.getData().get("text");


        //for background==========================================
        String key1 = remoteMessage.getData().get("key1");
        String key2 = remoteMessage.getData().get("key2");

        String body = remoteMessage.getData().get("body");
        String title = remoteMessage.getData().get("title");
        String image = remoteMessage.getData().get("image");
        String text = remoteMessage.getData().get("text");
        String clickAction = remoteMessage.getData().get("click_action");
        //for background==========================================


        NotificationHelper helper=new NotificationHelper(this);
//        helper.tiggerNotification(title,body);
//        helper.tiggerNotification(title,body,image);
//        helper.tiggerNotification(title,body,image,clickAction);
//        helper.tiggerNotification(title,body,image,clickAction,key1,key2);
        helper.tiggerNotification(title,body,image,clickAction,key1,key2,text);

        //for forground notification======================







    }
}
