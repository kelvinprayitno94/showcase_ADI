package com.hino.hearts.ui.appointment

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

class AppointmentDetailViewModel : ViewModel() {
    var backClicked: MutableLiveData<Boolean> = MutableLiveData()
    var saveClicked: MutableLiveData<Boolean> = MutableLiveData()

    var accountLivedata: MutableLiveData<String> = MutableLiveData()

    /*val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val service: OpportunityService = HinoService.create(OpportunityService::class.java)
    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()*/

    var id: Int = 0
    var accountName: String? = "Account Name Not Found"
    var opportunity: String? = "Opportunity Name"
    var activityDetail: String? = ""
    var opportunityEnabled: Boolean = false
    var pageType: Int = 0
    var pageTitle: Int = R.string.appointment_details

    fun onBackPressed() {
        backClicked.value = true
    }

    /*fun getOpportunity(opportunityId: Int) {
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
    }*/

    fun onSavePressed() {
        saveClicked.value = true
    }
}