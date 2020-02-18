package com.hino.hearts.ui.account.detail.opportunity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountDetailOpportunityAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_account_detail_opportunity.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountOpportunityFragment : BaseFragment<ActivityAccountDetailBinding>() {

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

    fun initObserver() {

    }

    fun initViewModel() {

    }

    fun initEvent() {

    }

    private fun initAdapter() {
        context?.let {
            adapter = AccountDetailOpportunityAdapter(it)

            rv_opportunty.adapter = adapter
            rv_opportunty.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
        }
    }


}