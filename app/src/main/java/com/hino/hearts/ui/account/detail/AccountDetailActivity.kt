package com.hino.hearts.ui.account.detail

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.ApprovalDocumentAdapter
import com.hino.hearts.adapter.ApprovalInvoiceAdapter
import com.hino.hearts.adapter.ApprovalProgressAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.databinding.ActivityAccountListBinding
import com.hino.hearts.databinding.ActivityApprovalDetailBinding
import com.hino.hearts.databinding.ActivityApprovalFilterBinding
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.detail.ApprovalDetailActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_account_list.*
import kotlinx.android.synthetic.main.activity_approval_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountDetailActivity : BaseActivity<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountDetailViewModel>()

    lateinit var approvalDocumentAdapter: ApprovalDocumentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_account_list)

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