package com.hino.hearts.ui.login

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.User
import com.hino.hearts.network.DataResponse
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.service.user.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class LoginViewModel : ViewModel() {
    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var loginTap: MutableLiveData<Boolean> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()

    var userService: UserService = HinoService.create(UserService::class.java)

    fun onLogin(employeeId: String, password: String) {
        showLoading.value = true

        CoroutineScope(Dispatchers.Main).launch {
            //login(employeeId, password)
            val handler = Handler()
            handler.postDelayed({
                showLoading.value = false
                loginTap.value = true
            }, 1000)
        }
    }

    private fun login(employeeId: String, password: String) {
        userService.getUserData()
            .enqueue(object : Callback<DataResponse<User>> {
                override fun onFailure(call: Call<DataResponse<User>>, t: Throwable) {
                    showLoading.value = false

                    Log.d("Exception", t.message.toString())
                }

                override fun onResponse(
                    call: Call<DataResponse<User>>,
                    response: Response<DataResponse<User>>
                ) {
                    showLoading.value = false

                    when (response.isSuccessful && response.body() != null) {
                        true -> {
                            user.postValue(response.body()?.data)
                        }
                        false -> {
                            Log.d("Exception", response.message())
                        }
                    }
                }
            })
    }

    internal fun getUser(): LiveData<User> {
        return user
    }
}