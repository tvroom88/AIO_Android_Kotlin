package com.aio.kotlin.utils

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aio.kotlin.activities.MainActivity
import com.google.firebase.messaging.RemoteMessage


//PendingIntent
class NotificationUtils(val context: Context) {

    /**
     *  Notification Channel을 등록하는 메서드
     *  id : 코드에서 채널을 관리하기 위한 이름
     *  name : 사용자에게 노출 시킬 이름
     *  context: applicationContext를 넣어준다.
     */
    fun addNotificationChannel(
        channelId: String,
        channelName: String,
        applicationContext: Context,
        remoteMessage: RemoteMessage
    ) {

        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        val builder: NotificationCompat.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager.getNotificationChannel(channelId) == null) {
                    val channel = NotificationChannel(
                        channelId,
                        channelName,
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                NotificationCompat.Builder(applicationContext, channelId)
            } else {
                NotificationCompat.Builder(applicationContext)
            }

        builder.setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setSmallIcon(R.drawable.alert_dark_frame)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.alert_dark_frame)

//        notificationManager.notify(1, builder.build())
    }
}