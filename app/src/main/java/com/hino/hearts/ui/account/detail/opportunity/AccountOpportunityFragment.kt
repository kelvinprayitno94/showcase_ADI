package com.hino.hearts.ui.account.detail.opportunity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountDetailOpportunityAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.opportunity.detail.OpportunityDetailActivity
import kotlinx.android.synthetic.main.fragment_account_detail_opportunity.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountOpportunityFragment(var data: List<AccountListResponse.OpportunityData>?) : BaseFragment<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountOpportunityViewModel>()

    lateinit var adapter: AccountDetailOpportunityAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_detail_opportunity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        context?.let {
            adapter = AccountDetailOpportunityAdapter(it, data, object : AccountDetailOpportunityAdapter.OnAdapterTap{
                override fun onTap(pos: Int) {
                    startActivity<OpportunityDetailActivity>(
                        OpportunityDetailActivity.PARAM_OPPORTUNITY_VALUE to data?.get(pos)?.opportunityValue?.toLong(),
                        OpportunityDetailActivity.PARAM_ACCOUNT_NAME to data?.get(pos)?.name,
                        "is_from_account" to true
                    )
                }

            })

            rv_opportunty.adapter = adapter
            rv_opportunty.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
        }
    }


}