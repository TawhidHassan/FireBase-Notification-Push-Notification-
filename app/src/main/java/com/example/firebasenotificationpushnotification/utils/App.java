package com.example.firebasenotificationpushnotification.utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.firebasenotificationpushnotification.model.Notification;

public class App extends Application {



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        NotificationChannel notificationChannel=new NotificationChannel(Constants.id,Constants.name, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription(Constants.description);

        NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(notificationChannel);

    }
}
