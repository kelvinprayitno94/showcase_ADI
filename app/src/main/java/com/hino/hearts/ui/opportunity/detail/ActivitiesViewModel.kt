package com.hino.hearts.ui.opportunity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.OpportunityVisitModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.opportunity.OpportunityResponse
import com.hino.hearts.network.service.opportunity.OpportunityService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class ActivitiesViewModel : ViewModel() {
    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val service: OpportunityService = HinoService.create(OpportunityService::class.java)
    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()

    var opportunityId: Int = 0
    val activityData: MutableLiveData<MutableList<OpportunityVisitModel>> = MutableLiveData()

    init {
        val items:MutableList<OpportunityVisitModel> = ArrayList()
        activityData.value = items
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
                    activityData.value = response.body()?.data!!.opportunityVisit
                }
                else {
                    errorBody.value = response.errorBody()
                }
            }
        })
    }
}