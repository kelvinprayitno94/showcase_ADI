package com.hino.hearts.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.util.UserDefaults
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


/**
 * Created by Dihardja Software on 2020-02-11.
 */
class HomeFragmentViewModel : ViewModel() {
    companion object {
        private const val FOUR = 4
        private const val FIVE = 5
        private const val ELEVEN = 11
        private const val TWELVE = 12
        private const val FIFTEEN = 15
        private const val SIXTEEN = 16
        private const val TWENTY = 20
        private const val TWENTY_ONE = 21
        private const val TWENTY_THREE = 23
        private const val ONE_HUNDRED = 100
    }

    var greetings: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var role: MutableLiveData<String> = MutableLiveData()
    var leftInfo: MutableLiveData<String> = MutableLiveData()
    var rightInfo: MutableLiveData<String> = MutableLiveData()

    var visitProgress: MutableLiveData<Int> = MutableLiveData()
    var visitProgressTotal: MutableLiveData<Int> = MutableLiveData()
    var visitProgressPercentage: MutableLiveData<Int> = MutableLiveData()

    var approvalRequestCount: MutableLiveData<String> = MutableLiveData()

    var homeMenuList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    init {
        greetings.value = getGreetingMessage()
        name.value = UserDefaults.getInstance().getString(UserDefaults.USER_NAME)
        role.value = UserDefaults.getInstance().getString(UserDefaults.USER_ROLE)

        when (role.value == "Sales") {
            true -> {
                leftInfo.value = "35"
                rightInfo.value = "Rp3.254.120"

                visitProgress.value = FOUR
                visitProgressTotal.value = FIVE
                visitProgressPercentage.value = calculateProgress()
            }
            false -> {
                leftInfo.value = "247"
                rightInfo.value = "Rp3.254.120"

                approvalRequestCount.value = "24"
            }
        }

        homeMenuList.value = initHomeMenu()
    }

    fun addVisitProgress() {
        when (visitProgress.value!! < visitProgressTotal.value!!) {
            true -> {
                visitProgress.value = visitProgress.value!! + 1
                visitProgressPercentage.value = calculateProgress()
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
            in SIXTEEN..TWENTY -> {
                return "Selamat sore"
            }
            in TWENTY_ONE..TWENTY_THREE -> {
                return "Selamat malam"
            }
        }
        return ""
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

    private fun initHomeMenu(): ArrayList<HomeMenu>{
        val homeMenuList: ArrayList<HomeMenu> = ArrayList()

        homeMenuList.add(HomeMenu(R.drawable.ic_close, R.string.catalogues))
        homeMenuList.add(HomeMenu(R.drawable.ic_close, R.string.accounts))
        homeMenuList.add(HomeMenu(R.drawable.ic_close, R.string.spare_part))
        homeMenuList.add(HomeMenu(R.drawable.ic_close, R.string.events))
        when(role.value != "Sales"){
            true-> {
                homeMenuList.add(HomeMenu(R.drawable.ic_close, R.string.approvals))
            }
        }

        return homeMenuList
    }
}