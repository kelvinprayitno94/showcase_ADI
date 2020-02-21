package com.hino.hearts.ui.account.detail.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel

class AccountContactViewModel : ViewModel() {
    var navNewContactLiveData = MutableLiveData<Boolean>()
    var navEditContactLiveData = MutableLiveData<Boolean>()

    fun addContactTap(){
        navNewContactLiveData.value = true
    }

    fun editContactTap(){
        navEditContactLiveData.value = true
    }
}