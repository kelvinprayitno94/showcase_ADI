package com.hino.hearts.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.ApprovalDocumentAdapter
import com.hino.hearts.databinding.ActivityAccountListBinding
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.detail.ApprovalDetailActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_account_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountListActivity : BaseActivity<ActivityAccountListBinding>() {

    private val viewModel by viewModel<AccountListActivityViewModel>()

    lateinit var approvalDocumentAdapter: ApprovalDocumentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_account_list)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        viewModel.init()
    }

    override fun initEvent() {


    }

    private fun initAdapter() {

        viewModel.documentLivedata.observe(this, Observer {

            approvalDocumentAdapter =
                ApprovalDocumentAdapter(this, it, object : ApprovalDocumentAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
                        startActivity(
                            Intent(
                                this@AccountListActivity,
                                ApprovalDetailActivity::class.java
                            )
                        )
                    }

                })

            val document = resources.getStringArray(R.array.approval_tab_document).toList()
            val branch = resources.getStringArray(R.array.approval_tab_branch).toList()

            for (index in document.indices) {
                it.docList.add(
                    ApprovalDocumentModel(
                        "PT. Dihardja",
                        document[index],
                        branch[index]
                    )
                )
            }

            rv_account.adapter = approvalDocumentAdapter
            rv_account.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_account.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        })
    }
}