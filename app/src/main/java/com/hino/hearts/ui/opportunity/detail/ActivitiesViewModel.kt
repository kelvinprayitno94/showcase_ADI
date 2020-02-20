package com.hino.hearts.ui.opportunity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.AppointmentActivityModel
import com.hino.hearts.model.HomeMenu

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class ActivitiesViewModel : ViewModel() {
    val activityData: MutableLiveData<MutableList<AppointmentActivityModel>> = MutableLiveData()

    init {
        val items:MutableList<AppointmentActivityModel> = ArrayList()
        items.add(AppointmentActivityModel(1, "2 Feb 2020", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur, ultricies mi in, suscipit lacus."))
        items.add(AppointmentActivityModel(2, "22 Jan 2020", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur, ultricies mi in, suscipit lacus."))
        items.add(AppointmentActivityModel(3, "2 Jan 2020", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur, ultricies mi in, suscipit lacus."))
        items.add(AppointmentActivityModel(4, "1 Jan 2020", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis sem purus. Pellentesque eleifend at lacus ac dignissim. Suspendisse ac risus efficitur, ultricies mi in, suscipit lacus."))
        activityData.value = items
    }
}