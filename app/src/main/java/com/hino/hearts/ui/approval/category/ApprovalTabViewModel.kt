package com.hino.hearts.ui.approval.category

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.approve.ApprovalListResponse
import com.hino.hearts.network.service.approval.ApprovalService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ApprovalTabViewModel : ViewModel() {

    val documentTypeList : ApprovalDocModel = ApprovalDocModel(ArrayList())

    val showCateTextLiveData = MutableLiveData<Boolean>()
    val animateArrorLiveData = MutableLiveData<Pair<Float, Float>>()
    val approvalListLiveData = MutableLiveData<ApprovalListResponse>()

    val documentLivedata = MutableLiveData<ApprovalDocModel>()

    var isOpen = false

    fun getTabTitle(){
        documentLivedata.postValue(ApprovalDocModel(ArrayList()))
    }

    fun showDocTypeSlidingView(view: Int
//                               , height: Float
    ){
        if (isOpen){
            showCateTextLiveData.value = false
            animateArrorLiveData.value = Pair(180f, 360f)
            isOpen = false
//            animateListLiveData.value = height
        }else{
            showCateTextLiveData.value = true
            animateArrorLiveData.value = Pair(0f, 180f)
            isOpen = true
//            animateListLiveData.value = 0f
        }
    }

    fun getApproval(){
        CoroutineScope(Dispatchers.IO).launch  {

            try {

                val call =
                    HinoService.create(ApprovalService::class.java).ApprovalList()

                val response = call.await()

                if (response.meta.success) {
                    approvalListLiveData.postValue(response)
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