package com.ralphmarondev.compose_notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHelper {

    private const val CHANNEL_ID = "compose_notification_channel"

    fun createChannel(context: Context) {
        val name = "Compose Notification"
        val description = "Notifications for Compose demo app"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(
            CHANNEL_ID,
            name,
            importance
        ).apply {
            this.description = description
        }

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        manager.createNotificationChannel(channel)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun simpleNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple Notification")
            .setContentText("This is a simple notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context)
            .notify(1, notification)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun detailedNotification(context: Context) {
        val moreDetails = "This is an example of detailed notification using BigTextStyle. " +
                "You can display longer messages that expand when the notification is opened."

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Detailed Notification")
            .setContentText("Tap to see more information")
            .setStyle(NotificationCompat.BigTextStyle().bigText(moreDetails))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context)
            .notify(2, notification)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun launchAppNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Launch App Notification")
            .setContentText("Tap this notification to open the app")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context)
            .notify(3, notification)
    }
}