package com.hino.hearts.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.hino.hearts.R
import com.hino.hearts.network.response.ErrorResponse
import okhttp3.ResponseBody

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class NetworkManager {

    companion object {
        private var instance: NetworkManager? = null

        fun getInstance(): NetworkManager {
            if (instance == null) {
                instance = NetworkManager()
            }
            return instance!!
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    fun handleResponse(context: Context, responseBody: ResponseBody) {
        var errorString = ""
        try {
            errorString = responseBody.string()
            val errorResponse: ErrorResponse = Gson().fromJson<ErrorResponse>(
                errorString,
                ErrorResponse::class.java
            )
            Toast.makeText(context, errorResponse.meta.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            if (responseBody.contentType()!!.type == "text" && responseBody.contentType()!!.subtype == "plain") {
                Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun handleErrorResponse(
        context: Context?,
        t: Throwable
    ) {
        if (t.cause != null) {
            Toast.makeText(
                context,
                context?.getString(R.string.server_not_available),
                Toast.LENGTH_SHORT
            ).show()

        } else if (t.localizedMessage != null) {
            Log.e("localizedMessage", t.localizedMessage!!)

        } else {
            if (context != null) {
                Toast.makeText(
                    context,
                    context.getString(R.string.server_not_available),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}