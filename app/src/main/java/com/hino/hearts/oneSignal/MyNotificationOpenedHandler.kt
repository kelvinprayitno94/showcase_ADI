package com.hino.hearts.oneSignal

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.hino.hearts.ui.approval.category.ApprovalTabActivity
import com.onesignal.OSNotificationAction
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal
import org.jetbrains.anko.startActivity


class MyNotificationOpenedHandler(var application: Context) : OneSignal.NotificationOpenedHandler {
    override fun notificationOpened(result: OSNotificationOpenResult?) {
        val actionType = result!!.action.type
        val data = result.notification.payload.additionalData
        var activityToBeOpened: String

        Log.d("One Signal Open", "action type ${actionType.name}, data $data")

        if (actionType.name == "Opened"){
            openApproval()
        }
    }

    fun openApproval(){
        var intent = Intent(application, ApprovalTabActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        application.startActivity(intent)
    }
}