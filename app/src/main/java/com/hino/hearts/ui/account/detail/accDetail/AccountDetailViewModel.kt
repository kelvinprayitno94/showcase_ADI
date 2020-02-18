package com.hino.hearts.ui.account.detail.accDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountDetailViewModel : ViewModel() {
    var navEditContactLiveData = MutableLiveData<Boolean>()

    fun editContactTap(){
        navEditContactLiveData.value = true
    }
}