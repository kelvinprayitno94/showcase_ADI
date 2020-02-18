package com.hino.hearts.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.util.UserDefaults

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class SplashViewModel : ViewModel(){
    var token: MutableLiveData<String> = MutableLiveData()

    init {
        token.value = UserDefaults.getInstance().getString(UserDefaults.TOKEN_KEY)
    }
}