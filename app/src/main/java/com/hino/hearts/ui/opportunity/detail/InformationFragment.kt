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
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }
}
