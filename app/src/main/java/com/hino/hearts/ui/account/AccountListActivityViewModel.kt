package com.hino.hearts.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.service.account.AccountService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AccountListActivityViewModel : ViewModel() {

    val documentLivedata = MutableLiveData<AccountListResponse>()

    fun init() {
        fetchAccountList()
    }

    fun fetchAccountList() {
        CoroutineScope(Dispatchers.IO).launch  {

            try {

                val call =
                    HinoService.create(AccountService::class.java).fetchAccountList("1", "10")

                val response = call.await()

                if (response.meta.success) {
                    documentLivedata.postValue(response)
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