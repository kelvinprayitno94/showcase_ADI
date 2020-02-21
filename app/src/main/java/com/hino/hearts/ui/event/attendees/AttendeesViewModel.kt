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

        attendeesList.add(Attendees(1, "PT Dihardja Software", "Sales: Abdul", true, false))
        attendeesList.add(Attendees(2, "PT Bumi Indah Indonesia", "Gabriel Salazar", true, false))
        attendeesList.add(Attendees(3, "PT Besi Baja Indonesia", "Cecelia Watson", true, true))
        attendeesList.add(Attendees(4, "PT Citra Guna Mandiri", "Marian Hunt", false, false))
        attendeesList.add(Attendees(5, "PT Dihardja Software", "Nell Townsend", false, false))

        return attendeesList
    }
}