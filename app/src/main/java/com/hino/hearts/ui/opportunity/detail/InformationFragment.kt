package com.hino.hearts.ui.opportunity.detail


import android.os.Bundle
import android.view.View
import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentOpportunityInformationBinding
import com.hino.hearts.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformationFragment : BaseFragment<FragmentOpportunityInformationBinding>() {

    private val viewModel by viewModel<InformationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initObserver()
        initViewModel()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_opportunity_information
    }

    private fun initData() {
        viewModel.accountName = activity!!.intent.getStringExtra(OpportunityDetailActivity.PARAM_ACCOUNT_NAME)
        viewModel.opportunityValue = activity!!.intent.getLongExtra(OpportunityDetailActivity.PARAM_OPPORTUNITY_VALUE, 0)
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }
}
