package com.hino.hearts.ui.account.detail.accDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentAccountDetailsBinding
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.accDetail.editAccount.EditAccountDetailActivity
import kotlinx.android.synthetic.main.fragment_account_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountDetailFragment : BaseFragment<FragmentAccountDetailsBinding>() {

    private val viewModel by viewModel<AccountDetailViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    fun initObserver() {
        viewModel.navEditContactLiveData.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(activity, EditAccountDetailActivity::class.java))
        })
    }

    fun initViewModel() {

    }

    fun initEvent() {
        btn_detail_edit_account_detail.setOnClickListener {
            viewModel.editContactTap()
        }
    }

    private fun initAdapter() {

    }


}