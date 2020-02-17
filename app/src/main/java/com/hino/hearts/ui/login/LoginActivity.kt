package com.hino.hearts.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityLoginBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.category.ApprovalTabActivity
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

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
        viewModel.loginTap.observe(this, Observer {
            startActivity<HomeActivity>()
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        btn_login.onClick {
//            viewModel.onLogin(
//                edittext_employee_id.text.toString(),
//                edittext_password.text.toString()
//            )
            val intent = Intent(this@LoginActivity, ApprovalTabActivity::class.java)
            startActivity(intent)
        }
    }
}
