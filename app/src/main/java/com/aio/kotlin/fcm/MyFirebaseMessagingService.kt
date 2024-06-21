package com.aio.kotlin.fcm

import android.Manifest
import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {


    // Create channel to show notifications.
    private val CHANNEL_ID = "aaa"
    private val CHANNEL_NAME = "bbb"

    // 토큰
    override fun onNewToken(@NonNull token: String) {
        super.onNewToken(token)
        Log.d("MyFcmService", "New token :: $token")
    }

    override fun onMessageReceived(@NonNull remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        //수신한 메시지를 처리

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        val builder: NotificationCompat.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(applicationContext)
        }

        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body

        Log.d("testtest", title.toString())

        builder.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.alert_dark_frame)

        val notification: Notification = builder.build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(1, notification)
        }
    }
}