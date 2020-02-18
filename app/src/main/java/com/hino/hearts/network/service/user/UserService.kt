package com.hino.hearts.network.service.user

import com.hino.hearts.network.response.user.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface UserService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("employeeId") employeeId: String?,
        @Field("password") password: String?
    ): Call<LoginResponse.Result>
}