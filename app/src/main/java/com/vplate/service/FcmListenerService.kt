package com.vplate.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.PowerManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vplate.R
import com.vplate.activity.MainActivity


class FcmListenerService : FirebaseMessagingService() {
    private val TAG = "FirebaseMsgService"

    override fun onMessageReceived(message: RemoteMessage?) {
        val from = message!!.from
        val data = message.data
        val title = data["title"]
        val msg = data["message"]
        if (message.getData().size > 0) {
            sendNotification(message.getData().get("message")!!);
        }
        if (message.getNotification() != null) {
            sendNotification(message.getNotification().getBody());
        }
        // 전달 받은 정보로 뭔가를 하면 된다.
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)//.setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pm = this.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, TAG)
        wakelock.acquire(5000)

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}