package com.hino.hearts.ui.opportunity.appointment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.network.response.visit.CreateVisitResponse
import com.hino.hearts.network.service.account.AccountService
import com.hino.hearts.network.service.visit.VisitRequestBody
import com.hino.hearts.network.service.visit.VisitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentDetailViewModel : ViewModel() {
    var saveClicked: MutableLiveData<Boolean> = MutableLiveData()

    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()
    var createVisitResponse: MutableLiveData<Boolean> = MutableLiveData()
    val documentLivedata = MutableLiveData<AccountListResponse>()

    var visitId: Int = 0

    var pageType: Int = 0
    var pageTitle: Int = R.string.appointment_details
    var selectedAccountId: MutableLiveData<Int> = MutableLiveData(-1)
    var selectedAccountName: MutableLiveData<String> = MutableLiveData("Account")
    var selectedAccountListData: AccountListResponse.AccListData? = null
    var selectedOpportunityId: MutableLiveData<Int> = MutableLiveData(-1)
    var selectedOpportunityName: MutableLiveData<String> = MutableLiveData("Opportunity")
    var activityDetail: MutableLiveData<String> = MutableLiveData("")
    var lockAccountOpportunity: Boolean = false
    var isReadOnlyMode: Boolean = false

    fun fetchAccountList() {
        CoroutineScope(Dispatchers.IO).launch  {
            showLoading.postValue(true)
            try {
                val call = HinoService.create(AccountService::class.java).fetchAccountList("1", "10")

                val response = call.await()
                if (response.meta.success) {
                    showLoading.postValue(false)
                    documentLivedata.postValue(response)
                }
            }
            catch (t: Throwable) {
                showLoading.postValue(false)
                responseError.postValue(t)
            }
        }
    }

    fun createVisit(userId: Int, opportunityId: Int, accountId: Int, description: String, type: String) {
        showLoading.value = true

        val service = HinoService.create(VisitService::class.java)
        service.createVisit(VisitRequestBody(userId, opportunityId, accountId, description, type)).enqueue(object :
            Callback<CreateVisitResponse.Result> {

            override fun onFailure(call: Call<CreateVisitResponse.Result>, t: Throwable) {
                showLoading.value = false
                responseError.value = t
                createVisitResponse.value = false
            }

            override fun onResponse(call: Call<CreateVisitResponse.Result>, response: Response<CreateVisitResponse.Result>) {
                showLoading.value = false

                if (response.isSuccessful && response.body()?.data != null && response.body()!!.meta.success) {
                    createVisitResponse.value = true
                }
                else {
                    errorBody.value = response.errorBody()
                    createVisitResponse.value = false
                }
            }
        })
    }

    fun onSavePressed() {
        saveClicked.value = true
    }
}