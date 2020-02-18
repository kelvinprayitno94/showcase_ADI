package com.hino.hearts.ui.account.detail.accDetail.editAccount

import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditAccountDetailActivity : BaseActivity<ActivityEditAccountDetailBinding>() {

    private val viewModel by viewModel<EditAccountDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_edit_account_detail)

        super.onCreate(savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {

    }

    override fun initEvent() {


    }

    private fun initAdapter() {

    }
}