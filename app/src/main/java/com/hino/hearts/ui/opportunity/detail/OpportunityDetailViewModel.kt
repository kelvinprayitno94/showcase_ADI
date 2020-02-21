package com.hino.hearts.ui.opportunity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import com.hino.hearts.network.service.opportunity.OpportunityService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpportunityDetailViewModel : ViewModel() {
    var backClicked: MutableLiveData<Boolean> = MutableLiveData()
    var addClicked: MutableLiveData<Boolean> = MutableLiveData()

    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val service: OpportunityService = HinoService.create(OpportunityService::class.java)
    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()

    var id: Int = 0
    var opportunityName: String? = "Opportunity Name"
    var accountName: String? = "Account Name Not Found"
    var opportunityValue: Long? = 0

    var data: MutableLiveData<OpportunityModel> = MutableLiveData()

    val addVisitButtonList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    init {
        val addButtonList: ArrayList<HomeMenu> = ArrayList()
        addButtonList.add(HomeMenu(R.drawable.ic_appointment, R.string.appointment))
        addButtonList.add(HomeMenu(R.drawable.ic_task, R.string.task))
        addButtonList.add(HomeMenu(R.drawable.ic_call_log, R.string.call_log))
        addVisitButtonList.value = addButtonList
    }

    fun onBackPressed() {
        backClicked.value = true
    }

    fun getOpportunity(opportunityId: Int) {
        showLoading.value = true

        service.getOpportunity(opportunityId).enqueue(object :
            Callback<OpportunityResponse.Result> {

            override fun onFailure(call: Call<OpportunityResponse.Result>, t: Throwable) {
                showLoading.value = false
                responseError.value = t
            }

            override fun onResponse(call: Call<OpportunityResponse.Result>, response: Response<OpportunityResponse.Result>) {
                showLoading.value = false

                if (response.isSuccessful && response.body()?.data != null && response.body()!!.meta.success) {
                    data.value = response.body()?.data!!
                }
                else {
                    errorBody.value = response.errorBody()
                }
            }
        })
    }

    fun onAddPressed() {
        addClicked.value = true
    }
}