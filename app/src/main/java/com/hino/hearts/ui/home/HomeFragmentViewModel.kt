package com.hino.hearts.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.model.VisitTarget
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.home.HomeDataResponse
import com.hino.hearts.network.service.home.HomeService
import com.hino.hearts.util.InterfaceManager
import com.hino.hearts.util.UserDefaults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class HomeFragmentViewModel : ViewModel() {
    companion object {
        private const val ELEVEN = 11
        private const val TWELVE = 12
        private const val FIFTEEN = 15
        private const val SIXTEEN = 16
        private const val EIGHTEEN = 18
        private const val NINETEEN = 19
        private const val TWENTY_THREE = 23
        private const val ONE_HUNDRED = 100
    }

    var greetings: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var role: MutableLiveData<String> = MutableLiveData()
    var leftInfo: MutableLiveData<String> = MutableLiveData()
    var rightInfo: MutableLiveData<String> = MutableLiveData()
    var todayDate: MutableLiveData<String> = MutableLiveData()
    var approvalRequestCount: MutableLiveData<String> = MutableLiveData()

    var visitProgress: MutableLiveData<Int> = MutableLiveData()
    var visitProgressTotal: MutableLiveData<Int> = MutableLiveData()
    var visitProgressPercentage: MutableLiveData<Int> = MutableLiveData()

    var homeSuccess: MutableLiveData<Boolean> = MutableLiveData()
    var showLoading: MutableLiveData<Boolean> = MutableLiveData()

    var visitTargetList: MutableLiveData<ArrayList<VisitTarget>> = MutableLiveData()
    var homeMenuList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()

    var homeService: HomeService = HinoService.create(HomeService::class.java)

    init {
        leftInfo.value = "-"
        rightInfo.value = "Rp-"

        greetings.value = getGreetingMessage()
        name.value = UserDefaults.getInstance().getString(UserDefaults.USER_NAME)
        role.value = UserDefaults.getInstance().getString(UserDefaults.USER_ROLE)

        when (role.value == "Sales") {
            true -> {
                val current = Calendar.getInstance().time
                todayDate.value = InterfaceManager.getInstance().convertStringFromDate(current)
            }
            false -> {
                leftInfo.value = "247"
                rightInfo.value = "Rp3.254.120"

                approvalRequestCount.value = "1"
            }
        }

        homeMenuList.value = initHomeMenu()
    }

    fun onGetHomeData(type: String){
        when(type){
            "custom"->{
                showLoading.value = true
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            getHomeData()
        }
    }

    private fun getHomeData() {
        CoroutineScope(Dispatchers.Main).launch {
            homeService.getHomeData().enqueue(object :
                Callback<HomeDataResponse.Result> {

                override fun onFailure(call: Call<HomeDataResponse.Result>, t: Throwable) {
                    showLoading.value = false

                    responseError.value = t
                }

                override fun onResponse(
                    call: Call<HomeDataResponse.Result>,
                    response: Response<HomeDataResponse.Result>
                ) {
                    showLoading.value = false

                    when (response.body()?.data != null && response.body()!!.meta.success && response.isSuccessful) {
                        true -> {
                            val data = response.body()?.data!!

                            leftInfo.value = data.monthlyVisit.toString()
                            rightInfo.value = formatCurrency(data.totalOpportunity)

                            if(UserDefaults.getInstance().getString(UserDefaults.USER_ROLE) == "Sales"){
                                visitProgressTotal.value = data.todayVisit.size
                                getTodayVisitData(data.todayVisit)
                            }

                            homeSuccess.value = true
                        }
                        false -> {
                            errorBody.value = response.errorBody()
                        }
                    }
                }

            })
        }
    }

    private fun getTodayVisitData(data: ArrayList<HomeDataResponse.TodayVisit>){
        val visitTargetList: ArrayList<VisitTarget> = ArrayList()

        for (i in 0 until data.size) {
            visitTargetList.add(VisitTarget(data[i].organization, data[i].visit))

            when(data[i].visit){
                true->{
                    visitProgress.value =+ 1
                }
            }
        }

        this.visitTargetList.value = visitTargetList
        visitProgressPercentage.value = calculateProgress()
    }

    private fun formatCurrency(amount: Int): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber = formatter.format(amount)

        return "Rp$formattedNumber"
    }

    private fun calculateProgress(): Int {
        return when (visitProgress.value != null && visitProgressTotal.value != null) {
            true -> {
                ((visitProgress.value!!.toDouble() / visitProgressTotal.value!!.toDouble()) * ONE_HUNDRED).roundToInt()
            }
            false -> {
                0
            }
        }
    }

    private fun getGreetingMessage(): String {
        val c: Calendar = Calendar.getInstance()

        when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..ELEVEN -> {
                return "Selamat pagi"
            }
            in TWELVE..FIFTEEN -> {
                return "Selamat siang"
            }
            in SIXTEEN..EIGHTEEN -> {
                return "Selamat sore"
            }
            in NINETEEN..TWENTY_THREE -> {
                return "Selamat malam"
            }
        }
        return ""
    }

    private fun initHomeMenu(): ArrayList<HomeMenu> {
        val homeMenuList: ArrayList<HomeMenu> = ArrayList()

        homeMenuList.add(HomeMenu(R.drawable.ic_catalogues, R.string.catalogues))
        homeMenuList.add(HomeMenu(R.drawable.ic_accounts, R.string.accounts))
        homeMenuList.add(HomeMenu(R.drawable.ic_spareparts, R.string.spare_part))
        homeMenuList.add(HomeMenu(R.drawable.ic_events, R.string.events))
        homeMenuList.add(HomeMenu(R.drawable.ic_approval, R.string.approvals))
        homeMenuList.add(HomeMenu(R.drawable.ic_opportunity, R.string.opportunities))

        return homeMenuList
    }
}