package com.hino.hearts.ui.opportunity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.opportunity.OpportunityListResponse
import com.hino.hearts.network.service.opportunity.OpportunityService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpportunityViewModel : ViewModel() {
    val backClicked: MutableLiveData<Boolean> = MutableLiveData()

    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val service: OpportunityService = HinoService.create(OpportunityService::class.java)
    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()

    val headers: MutableList<String> = ArrayList()
    val data: MutableLiveData<HashMap<String, MutableList<OpportunityModel>>> = MutableLiveData()

    init {
        //Dummy
        headers.add("Quotation")
        headers.add("Negotiation")
        headers.add("Contract")
        headers.add("Won")
        headers.add("Lost")

        val hashMap: HashMap<String, MutableList<OpportunityModel>> = HashMap()
        val size: Int = headers.size
        for (i in 0 until size) {
            val header = headers[i]
            val items: MutableList<OpportunityModel> = ArrayList()
            hashMap[header] = items
        }
        data.value = hashMap

        getAllOpportunities()
    }

    fun onBackPressed() {
        backClicked.value = true
    }

    private fun getAllOpportunities() {
        showLoading.value = true

        service.getAllOpportunities(1, null).enqueue(object : Callback<OpportunityListResponse.Result> {

            override fun onFailure(call: Call<OpportunityListResponse.Result>, t: Throwable) {
                showLoading.value = false
                responseError.value = t
            }

            override fun onResponse(call: Call<OpportunityListResponse.Result>, response: Response<OpportunityListResponse.Result>) {
                showLoading.value = false

                if (response.isSuccessful && response.body()?.data != null && response.body()!!.meta.success) {
                    val responseData = response.body()?.data!!

                    val hashMap: HashMap<String, MutableList<OpportunityModel>> = HashMap()
                    val size: Int = headers.size
                    for (i in 0 until size) {
                        val header = headers[i]
                        val items: MutableList<OpportunityModel> = ArrayList()
                        hashMap[header] = items
                    }

                    for (item in responseData) {
                        var list: MutableList<OpportunityModel>
                        if (hashMap.containsKey(item.stage))
                            list = hashMap[item.stage]!!
                        else {
                            list = ArrayList()
                            hashMap[item.stage] = list
                        }

                        list.add(item)
                    }
                    data.value = hashMap
                }
                else {
                    errorBody.value = response.errorBody()
                }
            }

        })
    }
}