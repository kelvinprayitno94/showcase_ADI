package com.hino.hearts.oneSignal

import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.hino.hearts.BaseApplication
import com.hino.hearts.R
import com.onesignal.NotificationExtenderService
import com.onesignal.OSNotificationReceivedResult
import java.math.BigInteger


class MyNotificationExtenderService : NotificationExtenderService() {
    override fun onNotificationProcessing(notification: OSNotificationReceivedResult?): Boolean {
        val overrideSettings = OverrideSettings()
        overrideSettings.extender = NotificationCompat.Extender { builder ->
            // Sets the background notification color to Red on Android 5.0+ devices.
            val icon = BitmapFactory.decodeResource(
                BaseApplication.instance.resources,
                R.mipmap.ic_launcher
            )
            builder.setLargeIcon(icon)
            builder.setColor(ContextCompat.getColor(BaseApplication.instance.applicationContext, R.color.colorPrimary))
        }

        val displayedResult = displayNotification(overrideSettings)
        Log.d(
            "OneSignalExample",
            "Notification displayed with id: " + displayedResult.androidNotificationId
        )

        return true
    }
}