package com.hino.movie.network

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.hino.movie.BaseApplication
import com.hino.hearts.R
import com.hino.hearts.ui.login.LoginActivity
import com.hino.movie.util.UserDefaults
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Dihardja Software on 2020-02-24.
 */
class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401) {
//            unsubscribeOneSignal()
            _root_ide_package_.com.hino.movie.util.UserDefaults.getInstance().clear()
            backgroundThreadShortToast(
                _root_ide_package_.com.hino.movie.BaseApplication.instance,
                _root_ide_package_.com.hino.movie.BaseApplication.instance.resources.getString(R.string.token_expired)
            )

            val intent =
                Intent(_root_ide_package_.com.hino.movie.BaseApplication.instance, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            _root_ide_package_.com.hino.movie.BaseApplication.instance.startActivity(intent)
        }

        return response
    }

    private fun backgroundThreadShortToast(
        context: Context?,
        msg: String?
    ) {
        if (context != null && msg != null) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}