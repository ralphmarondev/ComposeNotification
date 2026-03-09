package com.ralphmarondev.compose_notification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ralphmarondev.compose_notification.ui.theme.ComposeNotificationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationHelper.createChannel(this)

        enableEdgeToEdge()
        setContent {
            ComposeNotificationTheme {
                HomeScreen()
            }
        }
    }
}