package com.hino.hearts.network

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.hino.hearts.BaseApplication
import com.hino.hearts.R
import com.hino.hearts.ui.login.LoginActivity
import com.hino.hearts.util.UserDefaults
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
            UserDefaults.getInstance().clear()
            backgroundThreadShortToast(
                BaseApplication.instance,
                BaseApplication.instance.resources.getString(R.string.token_expired)
            )

            val intent =
                Intent(BaseApplication.instance, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            BaseApplication.instance.startActivity(intent)
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