package com.hino.hearts.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.model.VisitTarget
import com.hino.hearts.util.InterfaceManager
import com.hino.hearts.util.UserDefaults
import java.time.LocalDateTime
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
    var todayDate: MutableLiveData<String> = MutableLiveData()
    var approvalRequestCount: MutableLiveData<String> = MutableLiveData()

    var visitProgress: MutableLiveData<Int> = MutableLiveData()
    var visitProgressTotal: MutableLiveData<Int> = MutableLiveData()
    var visitProgressPercentage: MutableLiveData<Int> = MutableLiveData()

    var visitTargetList: MutableLiveData<ArrayList<VisitTarget>> = MutableLiveData()

    var homeMenuList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    init {
        greetings.value = getGreetingMessage()
        name.value = UserDefaults.getInstance().getString(UserDefaults.USER_NAME)
        role.value = UserDefaults.getInstance().getString(UserDefaults.USER_ROLE)

        when (role.value == "Sales") {
            false -> {
                leftInfo.value = "35"
                rightInfo.value = "Rp3.254.120"

                visitProgress.value = FOUR
                visitProgressTotal.value = FIVE
                visitProgressPercentage.value = calculateProgress()

                visitTargetList.value = initVisitTarget()
            }
            true -> {
                leftInfo.value = "247"
                rightInfo.value = "Rp3.254.120"

                approvalRequestCount.value = "1"
            }
        }

        homeMenuList.value = initHomeMenu()
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

    private fun initVisitTarget(): ArrayList<VisitTarget> {
        val visitTargetList: ArrayList<VisitTarget> = ArrayList()

        visitTargetList.add(VisitTarget("PT Dihardja Software", true))
        visitTargetList.add(VisitTarget("PT Citra Guna Mandiri", true))
        visitTargetList.add(VisitTarget("PT Solusi Indonesia", true))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))
        visitTargetList.add(VisitTarget("PT Dihardja Software", false))

        val current = Calendar.getInstance().time
        todayDate.value = InterfaceManager.getInstance().convertStringFromDate(current)

        return visitTargetList
    }
}