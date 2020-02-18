package com.hino.hearts.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.Notification
import com.hino.hearts.util.InterfaceManager
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Dihardja Software on 2020-02-17.
 */
class NotificationViewModel : ViewModel() {

    var notificationList: MutableLiveData<ArrayList<Notification>> = MutableLiveData()

    init {
        notificationList.value = initNotificationList()
    }

    private fun initNotificationList(): ArrayList<Notification> {
        val notificationList: ArrayList<Notification> = ArrayList()

        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        notificationList.add(
            Notification(
                1,
                "Notification Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date
            )
        )
        notificationList.add(
            Notification(
                2,
                "Notification Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date
            )
        )
        notificationList.add(
            Notification(
                3,
                "Notification Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date
            )
        )
        notificationList.add(
            Notification(
                4,
                "Notification Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date
            )
        )
        notificationList.add(
            Notification(
                5,
                "Notification Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date
            )
        )

        return notificationList
    }
}