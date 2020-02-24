package com.hino.hearts.ui.approval.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.ErrorResponse
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.network.service.account.AccountService
import com.hino.hearts.network.service.approval.ApprovalService
import com.hino.hearts.util.InterfaceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ApprovalDetailViewModel : ViewModel() {

    val iDataLiveData = MutableLiveData<ApprovalListResponse.ApprovalListData>()

    val showActionLiveData = MutableLiveData<Boolean>()
    val discountLiveData = MutableLiveData<String>()
    val soDateLiveData = MutableLiveData<String>()
    val totalAmountLiveData = MutableLiveData<String>()

    val errorLiveData = MutableLiveData<ErrorResponse>()

    var iData : ApprovalListResponse.ApprovalListData? = null

    fun checkUser(roleId: Int){
        showActionLiveData.value = roleId != 7
    }

    fun init(iData : ApprovalListResponse.ApprovalListData?){
        this.iData = iData
        iDataLiveData.value = this.iData
        discountLiveData.value = "Rp ${iData?.discount}"
        totalAmountLiveData.value = "Rp ${iData?.totalAmount}"

        val date = iData?.approval?.soDate


        soDateLiveData.value = InterfaceManager.getInstance().convertStringFromDate(InterfaceManager.getInstance().convertDateFromString(date))
    }

    fun approve(){
        CoroutineScope(Dispatchers.IO).launch  {

            try {

                val call =
                    HinoService.create(ApprovalService::class.java).Approve(iData?.id.toString(), 0)

                val response = call.await()

                if (response.meta.success) {
                    errorLiveData.postValue(response)
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