package com.example.firebasenotificationpushnotification.network;

import com.example.firebasenotificationpushnotification.model.NotificationReq;
import com.example.firebasenotificationpushnotification.model.NotificationResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationRequest {
    @Headers({"Content-Type:application/json","Authorization:key=AAAA4rqDFYA:APA91bFfd7shMMKXjA53EilVASvMJYq7Gm93UX_wrQJYJ37F-gUL-utP9L0rlkRd4OmlpVnjUZCYGiBQ8n9sfzmpXvut61Zq0mCaKcoGptsrZ8BVfcZoXdRaRhh2cqqFjfuA_RZ_iBQg"})
    @POST("send")
    Call<NotificationResponce> sent(@Body NotificationReq req);
}
