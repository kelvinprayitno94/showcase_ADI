package com.hino.hearts.ui.account.detail.vehicles.vehicleDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleDetailViewModel : ViewModel() {

    val categoryLiveData= MutableLiveData<String>()
    val typeLiveData= MutableLiveData<String>()

    val showTypePopupMenuLiveData =  MutableLiveData<List<String>>()
    val showPopupMenuLiveData =  MutableLiveData<List<String>>()

    fun showCategoryPopupMenu(list: List<String>){
        showPopupMenuLiveData.value = list
    }

    fun showTypeUnitPopupMenu(list: List<String>){
        showTypePopupMenuLiveData.value = list
    }

}