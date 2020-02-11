package com.hino.hearts.ui.login

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityLoginBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_login)

        initBottomsheet()
        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
        viewModel.loginTap.observe(this, Observer {
            finish()
            startActivity<HomeActivity>()
            overridePendingTransition(0, 0)
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        btn_login.onClick {
            if (validateField()) {
                viewModel.onLogin(
                    edittext_employee_id.text.toString(),
                    edittext_password.text.toString()
                )
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
