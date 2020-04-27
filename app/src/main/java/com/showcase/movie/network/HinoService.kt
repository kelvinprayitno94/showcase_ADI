package com.hino.movie.network

import com.hino.hearts.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class HinoService{
    companion object {
        private fun getRetrofit(): Retrofit {
            val httpBuilder = OkHttpClient.Builder()

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val client = httpBuilder
                .addInterceptor(_root_ide_package_.com.hino.movie.network.HinoHeaderInterceptor())
                .addInterceptor(_root_ide_package_.com.hino.movie.network.ResponseInterceptor())
                .addInterceptor(httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
        }

        fun <T> create(klass: Class<T>): T {
            return _root_ide_package_.com.hino.movie.network.HinoService.Companion.getRetrofit()
                .create(klass)
        }
    }
}