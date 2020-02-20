package com.hino.hearts.ui.event.eventinformation


import android.os.Bundle
import android.view.View

import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentEventInformationBinding
import com.hino.hearts.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventInformationFragment : BaseFragment<FragmentEventInformationBinding>() {

    private val viewModel by viewModel<EventInformationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_event_information
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }
}

