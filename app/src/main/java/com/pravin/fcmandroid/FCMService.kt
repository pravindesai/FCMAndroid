package com.pravin.fcmandroid

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

//add service and intent filter in AndroidManifest.xml
class FCMService: FirebaseMessagingService() {
    private val TAG: String = "**FCM Service"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e(TAG, "onMessageReceived: " )
        val intent = Intent(this, FCMMainActivity::class.java)
        val fcmActivityIntent:PendingIntent = PendingIntent.getActivity(this, App.FCM_NOTIFICATION_REUEST_CODE,intent, PendingIntent.FLAG_IMMUTABLE)

        if(remoteMessage.notification!=null){
            val title = remoteMessage.notification?.title
            val body = remoteMessage.notification?.body
            val imgUrl = remoteMessage.notification?.imageUrl

            val notificationBuilder =
                NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle(title).setContentText(body).setColor(Color.CYAN)
                .setContentIntent(fcmActivityIntent)


            val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (imgUrl != null) {
                val bitmap: Bitmap? = getBitmapfromUrl(imgUrl.toString())
                notificationBuilder.setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                    .setLargeIcon(bitmap)
            }

            notificationManager.notify(1,notificationBuilder.build())

        }
        if (remoteMessage.data.size>0){
            Log.e(TAG, "onMessageReceived: DATA "+remoteMessage.data )
            for (key in remoteMessage.data.keys){
                Log.e("**", "FCM DATA -> ${key}  ${remoteMessage.data.get(key)}" )
            }
        }

    }

    fun getBitmapfromUrl(imageUrl: String?): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            Log.e("awesome", "Error in getting notification image: " + e.localizedMessage)
            null
        }
    }

    override fun onMessageSent(p0: String) {
        super.onMessageSent(p0)
        Log.e(TAG, "onMessageSent: " )
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("**", "**onNewToken: "+p0 )
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.e(TAG, "onDeletedMessages: " )
    }

}