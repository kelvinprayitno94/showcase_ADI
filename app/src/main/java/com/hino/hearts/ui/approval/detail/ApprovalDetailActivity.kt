package com.hino.hearts.ui.approval.detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountDetailTabAdapter
import com.hino.hearts.adapter.ApprovalInvoiceAdapter
import com.hino.hearts.adapter.ApprovalProgressAdapter
import com.hino.hearts.databinding.ActivityApprovalDetailBinding
import com.hino.hearts.databinding.ActivityApprovalFilterBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_approval_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApprovalDetailActivity : BaseActivity<ActivityApprovalDetailBinding>() {

    private val viewModel by viewModel<ApprovalDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_approval_detail)

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
        val invoiceAdapter = ApprovalInvoiceAdapter(this)
        val progressAdapter = ApprovalProgressAdapter(this)

        rv_invoice.adapter = invoiceAdapter
        rv_invoice.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rv_approval_progress.adapter = progressAdapter
        rv_approval_progress.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}