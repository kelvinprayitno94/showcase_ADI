package com.hino.hearts.ui.opportunity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityOpportunityDetailBinding
import com.hino.hearts.ui.BaseActivity

class OpportunityDetailActivity : BaseActivity<ActivityOpportunityDetailBinding>() {

    private val mViewModel: OpportunityDetailViewModel by lazy { ViewModelProvider(this).get(OpportunityDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_opportunity_detail)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    override fun initEvent() {

    }
}