package com.hino.hearts.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class InterfaceManager {
    var interfaceManager: InterfaceManager? = null

    fun getInstance(): InterfaceManager? {
        if (interfaceManager == null) {
            interfaceManager = InterfaceManager()
        }
        return interfaceManager!!.interfaceManager
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}