package com.hino.hearts.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.network.HinoService
import com.hino.hearts.network.response.user.LoginResponse
import com.hino.hearts.network.service.user.UserService
import com.hino.hearts.util.UserDefaults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class LoginViewModel : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var errorBody: MutableLiveData<ResponseBody> = MutableLiveData()
    var responseError: MutableLiveData<Throwable> = MutableLiveData()

    var userService: UserService = HinoService.create(UserService::class.java)

    init {
        token.value = UserDefaults.getInstance().getString(UserDefaults.TOKEN_KEY)
        showLoading.value = false
    }

    fun onLogin(employeeId: String, password: String) {
        showLoading.value = true

        CoroutineScope(Dispatchers.Main).launch {
            login(employeeId, password)
        }
    }

    private fun login(employeeId: String, password: String) {

        userService.login(employeeId, password).enqueue(object : Callback<LoginResponse.Result> {

            override fun onFailure(call: Call<LoginResponse.Result>, t: Throwable) {
                showLoading.value = false
                responseError.value = t
            }

            override fun onResponse(
                call: Call<LoginResponse.Result>,
                response: Response<LoginResponse.Result>
            ) {
                showLoading.value = false

                Log.d("lalala", response.body().toString())

                when (response.body()?.data != null && response.body()!!.meta.success && response.isSuccessful) {
                    true -> {
                        val data = response.body()?.data!!

                        UserDefaults.getInstance().setString(UserDefaults.TOKEN_KEY, data.token)
                        UserDefaults.getInstance()
                            .setInt(UserDefaults.USER_ID, data.userData!!.id)
                        UserDefaults.getInstance()
                            .setString(UserDefaults.USER_NAME, data.userData.name)
                        UserDefaults.getInstance().setString(UserDefaults.USER_ROLE,
                            data.userData.role.name
                        )
                        data.userData.role.id.let {
                            UserDefaults.getInstance().setInt(UserDefaults.USER_ROLE_ID,
                                it
                            )
                        }

                        //dummy
//                        UserDefaults.getInstance()
//                            .setString(UserDefaults.USER_IMAGE_PATH, "xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg")
                        loginSuccess.value = true
                    }
                    false -> {
                        errorBody.value = response.errorBody()
                    }
                }
            }

        })
    }

//    internal fun getUser(): LiveData<User> {
//        return user
//    }
}