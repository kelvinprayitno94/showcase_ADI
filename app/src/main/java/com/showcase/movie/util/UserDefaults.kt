package com.showcase.movie.util

import android.content.Context
import android.content.SharedPreferences
import com.showcase.movie.BaseApplication

/**
 * Created by Dihardja Software on 2020-02-10.
 */
class UserDefaults {
    companion object {
        private var instance: UserDefaults? = null

        //key
        const val TOKEN_KEY = "service_token"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_ROLE_ID = "user_role_id"
        const val USER_ROLE = "user_role"
        const val USER_IMAGE_PATH = "user_image_path"

        fun getInstance(): UserDefaults {
            if (instance == null)
                instance =
                    UserDefaults()

            return instance!!
        }
    }

    private var preferences: SharedPreferences? = null

    init {
        preferences = BaseApplication.instance.getSharedPreferences("", Context.MODE_PRIVATE)
    }

    fun setInt(key: String?, value: Int) {
        val prefsEditor = getEditor()

        prefsEditor.putInt(key, value)
        prefsEditor.apply()
    }

    fun setLong(key: String?, value: Long) {
        val prefsEditor = getEditor()

        prefsEditor.putLong(key, value)
        prefsEditor.apply()
    }

    fun setString(key: String?, value: String?) {
        val prefsEditor = getEditor()

        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun setBoolean(key: String?, value: Boolean) {
        val prefsEditor = getEditor()

        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun remove(key: String?) {
        val prefsEditor = getEditor()

        prefsEditor.remove(key)
        prefsEditor.apply()
    }

    fun getString(key: String?): String? {
        val pref = getPreferences()

        return pref.getString(key, null)
    }

    fun getBool(key: String?): Boolean {
        val pref = getPreferences()

        return pref.getBoolean(key, false)
    }

    fun getBoolDefTrue(key: String?): Boolean {
        val pref = getPreferences()

        return pref.getBoolean(key, true)
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        val pref = getPreferences()

        return pref.getInt(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Int): Long {
        val pref = getPreferences()

        return pref.getLong(key, defaultValue.toLong())
    }

    fun clear() {
        val pref = getPreferences()

        pref.edit().clear().apply()
    }

    private fun getPreferences(): SharedPreferences {
        return preferences!!
    }

    private fun getEditor(): SharedPreferences.Editor {
        return getPreferences().edit()
    }
}