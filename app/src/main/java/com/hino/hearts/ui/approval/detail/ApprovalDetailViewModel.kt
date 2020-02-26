package com.hino.hearts.ui.approval.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.ErrorResponse
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.network.service.approval.ApprovalService
import com.hino.hearts.util.InterfaceManager
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class ApprovalDetailViewModel : ViewModel() {

    val iDataLiveData = MutableLiveData<ApprovalListResponse.ApprovalListData>()

    val showActionLiveData = MutableLiveData<Boolean>()
    val discountLiveData = MutableLiveData<String>()
    val soDateLiveData = MutableLiveData<String>()
    val totalAmountLiveData = MutableLiveData<String>()

    val errorLiveData = MutableLiveData<ErrorResponse>()

    val loadingLiveData = MutableLiveData<Boolean>()

    var iData: ApprovalListResponse.ApprovalListData? = null

    var roleID = 0

    fun checkUser(roleId: Int) {
        roleID = roleId
//        showActionLiveData.value = roleID != 7

        if (roleID != 7)
            showActionLiveData.value = canApprove()

    }

    fun init(iData: ApprovalListResponse.ApprovalListData?) {
        this.iData = iData
        iDataLiveData.value = this.iData
        discountLiveData.value = "Rp ${iData?.discount}"
        totalAmountLiveData.value = "Rp ${iData?.totalAmount}"

        val date = iData?.approval?.soDate


        soDateLiveData.value = InterfaceManager.getInstance()
            .convertStringFromDate(InterfaceManager.getInstance().convertDateFromString(date))
    }

    fun canApprove(): Boolean {
        iData?.approvalProgress?.iterator()?.forEach { data ->

            if (!data.approved!!) {
                return data.signRoleId == roleID
            }
        }

        return false
    }

    fun loading(flag: Boolean) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loadingLiveData.value = flag
            }
        }
    }

    fun approve() {

        loading(flag = true)

        CoroutineScope(Dispatchers.IO).launch {

            try {

                var index = 0

                if (roleID == 1) {
                    index = 1
                } else if (roleID == 0) {
                    index = 0
                }

                val call =
                    HinoService.create(ApprovalService::class.java)
                        .Approve(iData?.id.toString(), index)

                val response = call.await()

                if (response.meta.success) {
                    errorLiveData.postValue(response)
                    loading(flag = false)
                } else loading(flag = false)

            } catch (t: Throwable) {
                t.printStackTrace()
                loading(flag = false)
                when (t) {
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