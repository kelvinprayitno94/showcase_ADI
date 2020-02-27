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
    var eventId: MutableLiveData<Int> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()

    var showButtonText: MutableLiveData<String> = MutableLiveData()

    var event: MutableLiveData<Event> = MutableLiveData()

    fun initEvent() {
        showLoading.value = true

//        val current = Calendar.getInstance().time
//        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        when(eventId.value){
            1->{
                event.value = Event(
                    1,
                    "HINO Conference 2020",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
                    "22 Feb 2020",
                    "1"
                )
            }
            2->{
                event.value = Event(
                    1,
                    "BOD Meet Up",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
                    "22 Feb 2020",
                    "1"
                )
            }
            3->{
                event.value = Event(
                    1,
                    "HINO FJ 120 J Grand Launching",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
                    "22 Feb 2020",
                    "1"
                )
            }
            4->{
                event.value = Event(
                    1,
                    "HINO FJ 105 X Grand Launching",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
                    "22 Feb 2020",
                    "1"
                )
            }
            5->{
                event.value = Event(
                    1,
                    "HINO FJ 023 B Grand Launching",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur.",
                    "22 Feb 2020",
                    "1"
                )
            }
        }

        showLoading.value = false
    }
}