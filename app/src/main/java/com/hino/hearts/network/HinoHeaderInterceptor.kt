package com.hino.hearts.network

import com.hino.hearts.util.UserDefaults
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class HinoHeaderInterceptor : Interceptor {
    var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder =
            original.newBuilder().method(original.method, original.body)
        token = UserDefaults.getInstance().getString(UserDefaults.TOKEN_KEY)

        if (token != null) {
            builder.addHeader("token", token!!)
        }

        return chain.proceed(builder.build())
    }
}