package com.hino.hearts.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityLoginBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.category.ApprovalTabActivity
import com.hino.hearts.ui.home.HomeActivity
import com.hino.hearts.util.InterfaceManager
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel by viewModel<LoginViewModel>()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_login)

        initBottomsheet()
        initObserver()
        initViewModel()
        initEvent()
    }

    override fun onResume() {
        super.onResume()

        Log.d("lalala", viewModel.showLoading.value.toString())
    }

    override fun initObserver() {
        viewModel.loginSuccess.observe(this, Observer {
            layout_custom_loading.visibility = View.GONE
            finish()
            startActivity<HomeActivity>()
            overridePendingTransition(0, 0)
        })

        viewModel.errorBody.observe(this, Observer {
            layout_custom_loading.visibility = View.GONE
            NetworkManager.getInstance().handleResponse(context, it)
        })

        viewModel.responseError.observe(this, Observer {
            layout_custom_loading.visibility = View.GONE
            NetworkManager.getInstance().handleErrorResponse(context, it)
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        btn_login.onClick {
            when (validateField()) {
                true -> {
                    when(NetworkManager.getInstance().isInternetAvailable(context)){
                        true->{
                            layout_custom_loading.visibility = View.VISIBLE
                            viewModel.onLogin(
                                edittext_employee_id.text.toString(),
                                edittext_password.text.toString()
                            )
                        }
                        false->{
                            toast(getString(R.string.no_internet))
                        }
                    }
                }
            }
        }
    }

    private fun initBottomsheet() {
        val bottomUp: Animation = AnimationUtils.loadAnimation(
            this,
            R.anim.bottom_up
        )
        cl_login_bottomsheet.startAnimation(bottomUp)
        cl_login_bottomsheet.visibility = View.VISIBLE

        when(BuildConfig.FLAVOR == "staging"){
            true -> {
                edittext_employee_id.setText("U14022001")
                edittext_password.setText("password")
            }
        }
    }

    private fun validateField(): Boolean {
        return when {
            edittext_employee_id.text!!.isEmpty() && edittext_password.text!!.isEmpty() -> {
                inputlayout_employee_id.error = getString(R.string.employee_id_field_error)
                inputlayout_password.error = getString(R.string.password_field_error)
                false
            }
            edittext_employee_id.text!!.isEmpty() -> {
                inputlayout_employee_id.error = getString(R.string.employee_id_field_error)
                false
            }
            edittext_password.text!!.isEmpty() -> {
                inputlayout_password.error = getString(R.string.password_field_error)
                false
            }
            else -> {
                inputlayout_employee_id.isErrorEnabled = false
                inputlayout_password.isErrorEnabled = false
                true
            }
        }
    }
}
