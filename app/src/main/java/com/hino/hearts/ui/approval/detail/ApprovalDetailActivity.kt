package com.hino.hearts.ui.approval.detail

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountDetailTabAdapter
import com.hino.hearts.adapter.ApprovalInvoiceAdapter
import com.hino.hearts.adapter.ApprovalProgressAdapter
import com.hino.hearts.databinding.ActivityApprovalDetailBinding
import com.hino.hearts.databinding.ActivityApprovalFilterBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.DividerItemDecoration
import com.hino.hearts.util.UserDefaults
import kotlinx.android.synthetic.main.activity_approval_detail.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApprovalDetailActivity : BaseActivity<ActivityApprovalDetailBinding>() {

    private val viewModel by viewModel<ApprovalDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_approval_detail)

        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.iDataLiveData.observe(this, Observer {
            val invoiceAdapter = ApprovalInvoiceAdapter(this, it)
            val progressAdapter = ApprovalProgressAdapter(this, it)

            rv_invoice.adapter = invoiceAdapter
            rv_invoice.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_invoice.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider, null)))

            rv_approval_progress.adapter = progressAdapter
            rv_approval_progress.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        })

        viewModel.errorLiveData.observe(this, Observer {
            val builder = AlertDialog.Builder(this@ApprovalDetailActivity)

            // Set the alert dialog title
            builder.setTitle("")

            // Display a message on alert dialog
            builder.setMessage("Approval berhasil")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("OK"){dialog, which ->
                setResult(200)
                finish()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            dialog.setCancelable(false)

            dialog.show()
        })
    }

    override fun initViewModel() {
        viewModel.checkUser(UserDefaults.getInstance().getInt(UserDefaults.USER_ROLE_ID, 0))

        viewModel.init(intent.getParcelableExtra("data"))
    }

    override fun initEvent() {
        main_toolbar.setOnClickListener {
            finish()
        }

        btn_approve_approval.setOnClickListener {
            viewModel.approve()
        }

    }

    private fun initAdapter() {
    }
}