package com.hino.hearts.ui.opportunity.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.adapter.OpportunityDetailPagerAdapter
import com.hino.hearts.databinding.ActivityOpportunityDetailBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.activity_opportunity_detail.*

class OpportunityDetailActivity : BaseActivity<ActivityOpportunityDetailBinding>() {

    private val mViewModel: OpportunityDetailViewModel by lazy { ViewModelProvider(this).get(OpportunityDetailViewModel::class.java) }
    private var mAdapter: OpportunityDetailPagerAdapter? = null

    companion object {
        const val PARAM_OPPORTUNITY_ID: String = "opportunity_id"
        const val PARAM_OPPORTUNITY_TITLE: String = "opportunity_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_opportunity_detail)

        initData()
        initObserver()
        initViewModel()
        initLayout()
        initEvent()
    }

    private fun initData() {
        mViewModel.id = intent.getIntExtra(PARAM_OPPORTUNITY_ID, 0)
        mViewModel.title = intent.getStringExtra(PARAM_OPPORTUNITY_TITLE)
    }

    override fun initObserver() {
        val context: Context = this
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })

        mViewModel.showLoading.observe(this, Observer {
            layout_custom_loading.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })
        mViewModel.errorBody.observe(this, Observer {
            NetworkManager.getInstance().handleResponse(context, it)
        })
        mViewModel.responseError.observe(this, Observer {
            NetworkManager.getInstance().handleErrorResponse(context, it)
        })
        mViewModel.data.observe(this, Observer {

        })

        if (mViewModel.id > 0)
            mViewModel.getOpportunity(mViewModel.id)
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    override fun initEvent() {

    }

    private fun initLayout() {
        mAdapter = OpportunityDetailPagerAdapter(supportFragmentManager)
        vp_opportunity.adapter = mAdapter
    }
}