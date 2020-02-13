package com.hino.hearts.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.hino.hearts.util.UserDefaults

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class HomeViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData()
    var role: MutableLiveData<String> = MutableLiveData()
    var imagePath: MutableLiveData<String> = MutableLiveData()
    var versionName: MutableLiveData<String> = MutableLiveData()

    init {
        versionName.value = BuildConfig.VERSION_NAME

        //setup dummy user data
        UserDefaults.getInstance().setString(UserDefaults.USER_NAME, "Abdul Rahmat")
        UserDefaults.getInstance().setString(UserDefaults.USER_ROLE, "Sales")
        UserDefaults.getInstance()
            .setString(UserDefaults.USER_IMAGE_PATH, "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg")

        name.value = UserDefaults.getInstance().getString(UserDefaults.USER_NAME)
        role.value = UserDefaults.getInstance().getString(UserDefaults.USER_ROLE)
        imagePath.value = BuildConfig.IMAGE_URL + UserDefaults.getInstance().getString(UserDefaults.USER_IMAGE_PATH)
    }

}