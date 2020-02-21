package com.hino.hearts.ui.approval.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApprovalFilterViewModel : ViewModel() {

    val onFilterTap = MutableLiveData<Boolean>()

    fun apply(){
        onFilterTap.value = true
    }
}