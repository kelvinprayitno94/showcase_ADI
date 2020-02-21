package com.hino.hearts.ui.approval.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.util.InterfaceManager

class ApprovalDetailViewModel : ViewModel() {

    val iDataLiveData = MutableLiveData<ApprovalListResponse.ApprovalListData>()

    val showActionLiveData = MutableLiveData<Boolean>()
    val discountLiveData = MutableLiveData<String>()
    val soDateLiveData = MutableLiveData<String>()
    val totalAmountLiveData = MutableLiveData<String>()

    var iData : ApprovalListResponse.ApprovalListData? = null

    fun checkUser(roleId: Int){
        showActionLiveData.value = roleId > 1
    }

    fun init(iData : ApprovalListResponse.ApprovalListData?){
        this.iData = iData
        iDataLiveData.value = this.iData
        discountLiveData.value = "Rp ${iData?.discount}"
        totalAmountLiveData.value = "Rp ${iData?.totalAmount}"

        val date = iData?.approval?.soDate


        soDateLiveData.value = InterfaceManager.getInstance().convertStringFromDate(InterfaceManager.getInstance().convertDateFromString(date))
    }
}