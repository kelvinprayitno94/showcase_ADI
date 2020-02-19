package com.hino.hearts.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.Notification
import com.hino.hearts.util.InterfaceManager
import java.util.*

/**
 * Created by Dihardja Software on 2020-02-17.
 */
class NotificationDetailViewModel : ViewModel() {
    var notification: MutableLiveData<Notification> = MutableLiveData()

    init {
        notification.value = initNotification()
    }

    private fun initNotification(): Notification {
        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        val notification = Notification(
            1,
            "Notification Title",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
            date
        )

        return notification
    }
}