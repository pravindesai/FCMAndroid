package com.pravin.fcmandroid

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class App: Application() {
    companion object{
        val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"
        val FCM_NOTIFICATION_REUEST_CODE = 123
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O){
            val notificationChannel:NotificationChannel =
                NotificationChannel(FCM_CHANNEL_ID, "FCM_CH",NotificationManager.IMPORTANCE_HIGH )
            val notificationManager:NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}