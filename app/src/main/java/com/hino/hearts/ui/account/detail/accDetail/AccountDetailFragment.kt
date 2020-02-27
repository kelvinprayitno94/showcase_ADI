package com.hino.hearts.ui.account.detail.accDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentAccountDetailsBinding
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.accDetail.editAccount.EditAccountDetailActivity
import kotlinx.android.synthetic.main.fragment_account_details.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountDetailFragment(var data: AccountListResponse.AccountData?) :
    BaseFragment<FragmentAccountDetailsBinding>() {

    private val viewModel by viewModel<AccountDetailViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.navEditContactLiveData.observe(viewLifecycleOwner, Observer {
            startActivity<EditAccountDetailActivity>(
                "account_data" to data
            )
        })
    }

    override fun initViewModel() {
        viewModel.init(data)
    }

    override fun initEvent() {
        btn_detail_edit_account_detail.setOnClickListener {
            viewModel.editContactTap()
        }
    }

    private fun initAdapter() {

    }


}