package com.hino.hearts.ui.approval.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApprovalDetailViewModel : ViewModel() {

    val onFilterTap = MutableLiveData<Boolean>()

    fun apply(){
        onFilterTap.value = true
    }
}