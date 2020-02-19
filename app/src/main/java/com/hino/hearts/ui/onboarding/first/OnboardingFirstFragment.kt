package com.hino.hearts.ui.onboarding.first


import android.os.Bundle
import android.view.View

import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentOnboardingFirstBinding
import com.hino.hearts.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>() {

    private val viewModel by viewModel<OnboardingFirstViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_onboarding_first
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }
}
