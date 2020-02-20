package com.hino.hearts.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.Event
import com.hino.hearts.util.InterfaceManager
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class EventViewModel : ViewModel() {
    var eventList: MutableLiveData<ArrayList<Event>> = MutableLiveData()

    init {
        eventList.value = initEventList()
    }

    private fun initEventList(): ArrayList<Event> {
        val eventList: ArrayList<Event> = ArrayList()

        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        eventList.add(
            Event(
                1,
                "Event Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date,
                "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )
        eventList.add(
            Event(
                2,
                "Event Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date,
                "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )
        eventList.add(
            Event(
                3,
                "Event Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date,
                "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )
        eventList.add(
            Event(
                4,
                "Event Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date,
                "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )
        eventList.add(
            Event(
                5,
                "Event Title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                date,
                "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
            )
        )

        return eventList
    }
}