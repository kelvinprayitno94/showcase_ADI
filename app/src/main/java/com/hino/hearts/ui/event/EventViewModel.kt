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
//        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        eventList.add(
            Event(
                1,
                "HINO Conference  2020",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                "22 Feb 2020",
                "1"
            )
        )
        eventList.add(
            Event(
                2,
                "BOD Meet Up",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                "22 Feb 2020",
                "2"
            )
        )
        eventList.add(
            Event(
                3,
                "HINO FJ 120 J Grand Launching",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                "22 Feb 2020",
                "3"
            )
        )
        eventList.add(
            Event(
                4,
                "HINO FJ 105 X Grand Launching",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                "22 Feb 2020",
                "4"
            )
        )
        eventList.add(
            Event(
                5,
                "HINO FJ 023 B Grand Launching",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fermentum pretium ex ut maximus. Nulla sapien tellus, vestibulum at ultricies eu, hendrerit eu diam.",
                "22 Feb 2020",
                "5"
            )
        )

        return eventList
    }
}