package com.hino.hearts.ui.opportunity.detail


import android.os.Bundle
import android.view.View
import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentOpportunityInformationBinding
import com.hino.hearts.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformationFragment(opportunityId: Int) : BaseFragment<FragmentOpportunityInformationBinding>() {

    private val mViewModel by viewModel<InformationViewModel>()
    private val mOpportunityId = opportunityId

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_opportunity_information
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        mViewModel.opportunityId = mOpportunityId
        val accountName = activity!!.intent.getStringExtra(OpportunityDetailActivity.PARAM_ACCOUNT_NAME)
        if (accountName != null)
            mViewModel.accountName = accountName

        mViewModel.opportunityValue = activity!!.intent.getLongExtra(OpportunityDetailActivity.PARAM_OPPORTUNITY_VALUE, 0)
    }

    override fun initEvent() {
    }
}
