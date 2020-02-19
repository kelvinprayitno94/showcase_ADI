package com.hino.hearts.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountListAdapter
import com.hino.hearts.databinding.ActivityAccountListBinding
import com.hino.hearts.model.AccountListModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.account.detail.AccountDetailActivity
import com.hino.hearts.ui.approval.detail.ApprovalDetailActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_account_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountListActivity : BaseActivity<ActivityAccountListBinding>() {

    private val viewModel by viewModel<AccountListActivityViewModel>()

    lateinit var approvalDocumentAdapter: AccountListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_account_list)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.documentLivedata.observe(this, Observer {

            approvalDocumentAdapter =
                AccountListAdapter(this, it, object : AccountListAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
                        startActivity(
                            Intent(
                                this@AccountListActivity,
                                AccountDetailActivity::class.java
                            )
                        )
                    }

                })

            for (index in 0 until 6) {
                it.add(
                    AccountListModel(
                        "PT. Dihardja",
                        "Tangerang"
                    )
                )
            }

            rv_account.adapter = approvalDocumentAdapter
            rv_account.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_account.addItemDecoration(
                DividerItemDecoration(
                    resources.getDrawable(
                        R.drawable.divider,
                        null
                    )
                )
            )

        })
    }

    override fun initViewModel() {
        viewModel.init()
    }

    override fun initEvent() {


    }

    private fun initAdapter() {

    }
}