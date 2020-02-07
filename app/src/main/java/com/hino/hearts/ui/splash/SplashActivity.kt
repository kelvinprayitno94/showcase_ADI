package com.hino.hearts.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hino.hearts.R

class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        token = UserDefaults.getInstance().getString(UserDefaults.TOKEN_KEY)
//
//        val handler = Handler()
//        handler.postDelayed({
//            finish()
//            //Check if has login but new user go to create account, else if has login and not new user go to home, else welcome
//            if (token != null) {
//                startActivity(Intent(this, HomeActivity::class.java))
//                UserDefaults.getInstance().setBoolean(UserDefaults.IS_HOME, true)
//            } else {
//                if (isFirstTime()) {
//                    startActivity(
//                        Intent(
//                            this,
//                            WelcomeActivity::class.java
//                        )
//                    )
//                } else {
//                    startActivity(Intent(this, HomeActivity::class.java))
//                    UserDefaults.getInstance().setBoolean(UserDefaults.IS_HOME, true)
//                }
//            }
//        }, 2000)
    }

//    private fun isFirstTime(): Boolean {
//        val preferences = getPreferences(Context.MODE_PRIVATE)
//        val ranBefore = preferences.getBoolean("RanBefore", false)
//        if (!ranBefore) { // first time
//            val editor = preferences.edit()
//            editor.putBoolean("RanBefore", true)
//            editor.apply()
//        }
//        return !ranBefore
//    }
}
