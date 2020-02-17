package com.hino.hearts.ui.approval.category

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel

class ApprovalTabViewModel : ViewModel() {

    val documentTypeList : ApprovalDocModel = ApprovalDocModel(ArrayList())

    val showCateTextLiveData = MutableLiveData<Boolean>()
    val animateArrorLiveData = MutableLiveData<Pair<Float, Float>>()
    val animateListLiveData = MutableLiveData<Float>()

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

    fun showSlideDown(){
        animateListLiveData.value = 0f
    }
}