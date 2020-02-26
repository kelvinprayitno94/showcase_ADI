package com.hino.hearts.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.Event
import com.hino.hearts.util.InterfaceManager
import java.util.*

/**
 * Created by Dihardja Software on 2020-02-19.
 */
class EventDetailViewModel : ViewModel(){
    var showLoading: MutableLiveData<Boolean> = MutableLiveData()

    var event: MutableLiveData<Event> = MutableLiveData()

    var showButtonText: MutableLiveData<String> = MutableLiveData()

    init {
        event.value = initEvent()

        showLoading.value = false
    }

    private fun initEvent(): Event {
        showLoading.value = true

        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        val event = Event(
            1,
            "HINO Conference 2020",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
            date,
            "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
        )

        return event
    }
}