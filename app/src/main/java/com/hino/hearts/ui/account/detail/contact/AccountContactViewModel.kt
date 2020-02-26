package com.hino.hearts.ui.account.detail.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.network.response.account.AccountListResponse

class AccountContactViewModel : ViewModel() {
    var navNewContactLiveData = MutableLiveData<Boolean>()
    var navEditContactLiveData = MutableLiveData<AccountListResponse.ContactData?>()

    fun addContactTap(){
        navNewContactLiveData.value = true
    }

    fun editContactTap(data: AccountListResponse.ContactData?){
        navEditContactLiveData.value = data
    }
}