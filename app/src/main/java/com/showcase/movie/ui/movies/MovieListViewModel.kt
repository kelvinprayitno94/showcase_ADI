package com.showcase.movie.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.showcase.movie.util.UserDefaults

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
        name.value = _root_ide_package_.com.showcase.movie.util.UserDefaults.getInstance().getString(
            _root_ide_package_.com.showcase.movie.util.UserDefaults.USER_NAME)
        role.value = _root_ide_package_.com.showcase.movie.util.UserDefaults.getInstance().getString(
            _root_ide_package_.com.showcase.movie.util.UserDefaults.USER_ROLE)
        roleId.value = _root_ide_package_.com.showcase.movie.util.UserDefaults.getInstance().getInt(
            _root_ide_package_.com.showcase.movie.util.UserDefaults.USER_ROLE_ID, 0)
//        imagePath.value = BuildConfig.IMAGE_URL + UserDefaults.getInstance().getString(UserDefaults.USER_IMAGE_PATH)

        when(roleId.value == 7){
            true->{
                addVisitButtonList.value = initAddButtonList()
            }
        }
    }

    fun logout(){
        _root_ide_package_.com.showcase.movie.util.UserDefaults.getInstance().clear()
    }

    private fun initAddButtonList(): ArrayList<HomeMenu> {
        val addButtonList: ArrayList<HomeMenu> = ArrayList()

        addButtonList.add(HomeMenu(R.drawable.ic_appointment, R.string.appointment))
        addButtonList.add(HomeMenu(R.drawable.ic_task, R.string.task))
        addButtonList.add(HomeMenu(R.drawable.ic_call_log, R.string.call_log))

        return addButtonList
    }
}