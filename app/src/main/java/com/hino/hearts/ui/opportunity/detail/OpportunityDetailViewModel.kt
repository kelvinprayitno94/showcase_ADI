package com.hino.hearts.ui.opportunity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.model.OpportunityVisitModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.opportunity.ChangeStatusResponse
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import com.hino.hearts.network.service.opportunity.OpportunityService
import com.hino.hearts.network.service.opportunity.StatusRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpportunityDetailViewModel : ViewModel() {
    var addClicked: MutableLiveData<Boolean> = MutableLiveData()

    var opportunityId: Int = 0
    var opportunityName: String? = "Opportunity Name"
    var accountName: String? = "Account Name Not Found"
    var opportunityValue: Long? = 0
    var opportunity: OpportunityModel? = null

    var data: MutableLiveData<List<OpportunityVisitModel>> = MutableLiveData()

    val addVisitButtonList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    init {
        val addButtonList: ArrayList<HomeMenu> = ArrayList()
        addButtonList.add(HomeMenu(R.drawable.ic_appointment, R.string.appointment))
        addButtonList.add(HomeMenu(R.drawable.ic_task, R.string.task))
        addButtonList.add(HomeMenu(R.drawable.ic_call_log, R.string.call_log))
        addVisitButtonList.value = addButtonList
    }

    fun onAddPressed() {
        addClicked.value = true
    }
}