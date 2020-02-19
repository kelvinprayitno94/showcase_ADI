package com.hino.hearts.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.AccountListModel

class AccountListActivityViewModel : ViewModel() {

    val documentLivedata = MutableLiveData<MutableList<AccountListModel>>()

    fun init() {
        documentLivedata.value = ArrayList()
    }
}