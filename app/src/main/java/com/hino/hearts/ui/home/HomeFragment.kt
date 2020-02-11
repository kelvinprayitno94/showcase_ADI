package com.hino.hearts.ui.home

import android.os.Bundle
import android.view.View

import com.hino.hearts.R
import com.hino.hearts.databinding.FragmentHomeBinding
import com.hino.hearts.ui.BaseFragment
import kotlinx.android.synthetic.main.layout_toolbar_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }
}
