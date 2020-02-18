package com.hino.hearts.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel

class AccountListActivityViewModel : ViewModel() {

    val documentLivedata = MutableLiveData<ApprovalDocModel>()

    fun init(){
        documentLivedata.value = ApprovalDocModel(ArrayList())
    }
}