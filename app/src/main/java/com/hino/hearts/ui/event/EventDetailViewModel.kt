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
    var event: MutableLiveData<Event> = MutableLiveData()

    init {
        event.value = initEvent()
    }

    private fun initEvent(): Event {
        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        val event = Event(
            1,
            "HINO Conference 2020",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam",
            date,
            "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
        )

        return event
    }
}