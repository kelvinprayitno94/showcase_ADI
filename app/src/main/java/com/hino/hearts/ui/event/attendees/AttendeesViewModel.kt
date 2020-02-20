package com.hino.hearts.ui.event.attendees

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.Attendees

/**
 * Created by Dihardja Software on 2020-02-20.
 */
class AttendeesViewModel : ViewModel(){
    var attendeesList: MutableLiveData<ArrayList<Attendees>> = MutableLiveData()

    init {
        attendeesList.value = initAttendees()
    }

    private fun initAttendees(): ArrayList<Attendees> {
        val attendeesList: ArrayList<Attendees> = ArrayList()

        attendeesList.add(Attendees(1, "PT Dihardja Software", "Abdul Rahmat", true, false))
        attendeesList.add(Attendees(2, "PT Dihardja Software", "Abdul Salim", true, false))
        attendeesList.add(Attendees(3, "PT Dihardja Software", "Abdul Bena", true, true))
        attendeesList.add(Attendees(4, "PT Dihardja Software", "Abdul Siji", false, false))
        attendeesList.add(Attendees(5, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(6, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(7, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(8, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(9, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(10, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(11, "PT Dihardja Software", "Abdul Loro", false, false))
        attendeesList.add(Attendees(12, "PT Dihardja Software", "Abdul Loro", false, false))

        return attendeesList
    }
}