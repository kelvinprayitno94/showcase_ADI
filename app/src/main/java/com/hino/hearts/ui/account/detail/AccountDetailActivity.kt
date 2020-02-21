package com.hino.hearts.ui.account.detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountDetailTabAdapter
import com.hino.hearts.adapter.AccountTabAdapter
import com.hino.hearts.adapter.ApprovalDocTypeFilterAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.model.ApprovalDocModel
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.ConstantManager
import kotlinx.android.synthetic.main.activity_account_detail.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountDetailActivity : BaseActivity<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountDetailViewModel>()

    lateinit var tabAdapter: AccountDetailTabAdapter
    lateinit var approvalDocTypeFilterAdapter: AccountTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_account_detail)

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
        main_toolbar.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        var titles = resources.getStringArray(R.array.account_tab_title).toList()
        tabAdapter = AccountDetailTabAdapter(
            supportFragmentManager,
            titles,
            intent.getParcelableExtra(ConstantManager.INTENT_ACC_DATA)
        )

        vp_account_detail_tab.adapter = tabAdapter
        tab_layout_account_detail.setupWithViewPager(vp_account_detail_tab)

        val tabItem = ApprovalDocModel(ArrayList())

        tabItem.prevSelected = 0

        for (index in titles.iterator()) {
            tabItem.docList.add(
                ApprovalDocumentModel(
                    index,
                    "",
                    ""
                )
            )
        }

        approvalDocTypeFilterAdapter = AccountTabAdapter(this, tabItem,
            object : AccountTabAdapter.OnAdapterTap {
                override fun onTap(pos: Int) {
                    tabItem.prevSelected = pos
                    approvalDocTypeFilterAdapter.notifyDataSetChanged()

                    vp_account_detail_tab.setCurrentItem(pos, true)
                }

            })

        rv_tab_layout_acc_detail.adapter = approvalDocTypeFilterAdapter
        rv_tab_layout_acc_detail.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }
}