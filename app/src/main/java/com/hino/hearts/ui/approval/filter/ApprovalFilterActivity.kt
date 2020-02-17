package com.hino.hearts.ui.approval.filter

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.adapter.SpinnerAdapter
import com.hino.hearts.databinding.ActivityApprovalFilterBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_approval_filter.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApprovalFilterActivity : BaseActivity<ActivityApprovalFilterBinding>() {

    private val viewModel by viewModel<ApprovalFilterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_approval_filter)

        super.onCreate(savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.onFilterTap.observe(this, Observer {
//            val i = Intent()
//            i.putExtra("branch", )
            finish()
//            setResult()
        })
    }

    override fun initViewModel() {

    }

    override fun initEvent() {
        btn_apply_filter.onClick {
            viewModel.apply()
        }

    }

    private fun initAdapter() {
        val branch = resources.getStringArray(R.array.approval_tab_branch).toList()
        val branchAdapter = SpinnerAdapter(this, R.layout.spinner, branch)
        val accountAdapter = SpinnerAdapter(this, R.layout.spinner, branch)
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_branch.adapter = branchAdapter
        sp_account.adapter = accountAdapter
    }
}