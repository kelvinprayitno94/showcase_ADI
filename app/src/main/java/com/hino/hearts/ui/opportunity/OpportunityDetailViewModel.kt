package com.hino.hearts.ui.opportunity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.OpportunityModel
import kotlin.random.Random

class OpportunityDetailViewModel : ViewModel() {
    var title: String = "Opportunity Detail"
    var data: OpportunityModel.OpportunityData? = null
    var backClicked: MutableLiveData<Boolean> = MutableLiveData()

    init {

    }

    fun onBackPressed() {
        backClicked.value = true
    }
}