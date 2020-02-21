package com.hino.hearts.ui.event.eventinformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Dihardja Software on 2020-02-20.
 */
class EventInformationViewModel : ViewModel(){
    var date: MutableLiveData<String> = MutableLiveData()
    var time: MutableLiveData<String> = MutableLiveData()
    var venue: MutableLiveData<String> = MutableLiveData()
    var location: MutableLiveData<String> = MutableLiveData()

    init {
        date.value = "14 Feb 2020"
        time.value = "09:00 AM"
        venue.value = "HINO Indonesia"
        location.value = "Jl. Gatot Subroto Km. 8, 5, Manis Jaya, Kec. Jatiuwung, Kota Tangerang, Banten 15111"
    }
}