package com.kay.prog.ayim.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kay.prog.ayim.ui.main.MainActivity

class MyFireBaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("ID", message.data["id"])
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("0", "default", NotificationManager.IMPORTANCE_LOW)
        val builder = NotificationCompat.Builder(applicationContext, "0")

        notificationManager.createNotificationChannel(channel)

        val notification = builder
            .setContentTitle(message.data["title"])
            .setContentText(message.data["text"])
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}