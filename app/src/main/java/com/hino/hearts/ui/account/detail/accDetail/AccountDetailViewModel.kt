package com.hino.hearts.ui.account.detail.accDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.account.AccountDetailResponse
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.service.account.AccountService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AccountDetailViewModel : ViewModel() {

    var navEditContactLiveData = MutableLiveData<Boolean>()

    var accNameLiveData = MutableLiveData<String>()
    var addressLiveData = MutableLiveData<String>()
    var cityLiveData = MutableLiveData<String>()
    var dataLiveData = MutableLiveData<AccountListResponse.AccountData>()

    var accDetailsLiveData = MutableLiveData<AccountDetailResponse>()

    fun editContactTap(){
        navEditContactLiveData.value = true
    }

    fun init(data: AccountListResponse.AccountData?){
        accNameLiveData.value = data?.accountName
        dataLiveData.value = data
    }

    fun fetchAccountDetail(){
        CoroutineScope(Dispatchers.IO).launch  {

            try {

                val call =
                    HinoService.create(AccountService::class.java).fetchAccountDetail()

                val response = call.await()

                if (response.meta.success) {
                    accDetailsLiveData.postValue(response)
                }

            } catch (t: Throwable){
                t.printStackTrace()
                when(t){
                    is IOException -> {

                    }
                    is HttpException -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }
}