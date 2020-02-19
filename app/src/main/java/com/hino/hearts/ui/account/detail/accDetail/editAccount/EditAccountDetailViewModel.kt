package com.hino.hearts.ui.account.detail.accDetail.editAccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditAccountDetailViewModel : ViewModel() {

    val businessFieldLiveData = MutableLiveData<String>()
    val cityLiveData = MutableLiveData<String>()
    val showCityPopupMenuLiveData =  MutableLiveData<List<String>>()
    val showPopupMenuLiveData =  MutableLiveData<List<String>>()

    fun showCityPopupMenu(list: List<String>){
        showPopupMenuLiveData.value = list
    }

    fun showBusinessPopupMenu(list: List<String>){
        showCityPopupMenuLiveData.value = list
    }

    fun setBusinessFieldText(text: String){
        businessFieldLiveData.value = text
    }

    fun setCityText(text: String){
        cityLiveData.value = text
    }

}