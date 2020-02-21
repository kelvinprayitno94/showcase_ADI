package com.hino.hearts.network.service.home

import com.hino.hearts.network.response.home.HomeDataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Dihardja Software on 2020-02-20.
 */
interface HomeService {
    @GET("home")
    fun getHomeData(): Call<HomeDataResponse.Result>
}