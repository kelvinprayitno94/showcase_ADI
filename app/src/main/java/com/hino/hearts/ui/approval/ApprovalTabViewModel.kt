package com.hino.hearts.ui.approval

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel

class ApprovalTabViewModel : ViewModel() {

    val documentTypeList : ApprovalDocModel = ApprovalDocModel(ArrayList())

    val documentLivedata = MutableLiveData<ApprovalDocModel>()

    fun getTabTitle(){
        documentLivedata.postValue(ApprovalDocModel(ArrayList()))
    }
}