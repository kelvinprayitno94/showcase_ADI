package com.hino.hearts.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.hino.hearts.model.HomeMenu
import com.hino.hearts.util.UserDefaults

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class HomeViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData()
    var role: MutableLiveData<String> = MutableLiveData()
    var roleId: MutableLiveData<Int> = MutableLiveData()
    var imagePath: MutableLiveData<String> = MutableLiveData()
    var versionName: MutableLiveData<String> = MutableLiveData()

    var addVisitButtonList: MutableLiveData<ArrayList<HomeMenu>> = MutableLiveData()

    init {
        versionName.value = BuildConfig.VERSION_NAME
        name.value = UserDefaults.getInstance().getString(UserDefaults.USER_NAME)
        role.value = UserDefaults.getInstance().getString(UserDefaults.USER_ROLE)
        roleId.value = UserDefaults.getInstance().getInt(UserDefaults.USER_ROLE_ID, 0)
//        imagePath.value = BuildConfig.IMAGE_URL + UserDefaults.getInstance().getString(UserDefaults.USER_IMAGE_PATH)

        when(roleId.value == 7){
            true->{
                addVisitButtonList.value = initAddButtonList()
            }
        }
    }

    fun logout(){
        UserDefaults.getInstance().clear()
    }

    private fun initAddButtonList(): ArrayList<HomeMenu> {
        val addButtonList: ArrayList<HomeMenu> = ArrayList()

        addButtonList.add(HomeMenu(R.drawable.ic_appointment, R.string.appointment))
        addButtonList.add(HomeMenu(R.drawable.ic_task, R.string.task))
        addButtonList.add(HomeMenu(R.drawable.ic_call_log, R.string.call_log))

        return addButtonList
    }
}