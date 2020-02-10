package com.hino.hearts.network.service.user

import com.hino.hearts.model.User
import com.hino.hearts.network.DataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface UserService {
    @GET("users")
    fun getUserData(): Call<DataResponse<User>>
}