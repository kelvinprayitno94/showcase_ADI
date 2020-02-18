package com.hino.hearts.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.inputmethod.InputMethodManager
import com.hino.hearts.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class InterfaceManager {

    companion object {
        private var instance: InterfaceManager? = null

        fun getInstance(): InterfaceManager {
            if (instance == null) {
                instance = InterfaceManager()
            }
            return instance!!
        }
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun isTablet(context: Context): Boolean? {
        return context.resources.getBoolean(R.bool.isTab)
    }

    fun convertDateFromString(dateInString: String?): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        format.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = Date()
        try {
            if(dateInString != null){
                date = format.parse(dateInString)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    fun convertStringFromDate(date: Date?):String{
        val format = SimpleDateFormat("dd MMM yyyy")
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(date)
    }
}