package com.hino.hearts.ui.splash

//import android.content.Context
//import android.os.Bundle
//import android.os.Handler
//import androidx.lifecycle.Observer
//import com.hino.hearts.R
//import com.hino.hearts.databinding.ActivitySplashBinding
//import com.hino.hearts.ui.BaseActivity
//import com.hino.hearts.ui.account.AccountListActivity
//import org.jetbrains.anko.startActivity
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class SplashActivity : BaseActivity<ActivitySplashBinding>() {
//
//    companion object {
//        private const val TWO_THOUSANDS: Long = 2000
//    }
//
//    private val viewModel by viewModel<SplashViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setBinding(R.layout.activity_splash)
//
//        initObserver()
//        initViewModel()
//        initEvent()
//    }
//
//    override fun initObserver() {
//        viewModel.token.observe(this, Observer {
//            when (it != null) {
//                true -> {
//                    redirect("home")
//                }
//                false -> {
//                    when (isFirstTime()) {
//                        true -> {
//                            redirect("onboarding")
//                        }
//                        false -> {
//                            redirect("login")
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    override fun initViewModel() {
//        binding.viewModel = viewModel
//    }
//
//    override fun initEvent() {
//    }
//
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
//
//    private fun redirect(page: String){
//        val handler = Handler()
//        handler.postDelayed({
//            finish()
//            /*when(page){
//                "home"->{
//                    startActivity<HomeActivity>()
//                }
//                "onboarding"->{
//                    startActivity<LoginActivity>()
//                }
//                "login"->{
//                    startActivity<LoginActivity>()
//                }
//            }*/
//            startActivity<AccountListActivity>()
//            overridePendingTransition(0, 0)
//        }, TWO_THOUSANDS)
//    }
//}
