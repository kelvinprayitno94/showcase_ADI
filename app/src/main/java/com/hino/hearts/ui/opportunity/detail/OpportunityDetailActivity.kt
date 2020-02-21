package com.hino.hearts.ui.opportunity.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.hino.hearts.R
import com.hino.hearts.adapter.AddVisitButtonAdapter
import com.hino.hearts.adapter.OpportunityDetailPagerAdapter
import com.hino.hearts.databinding.ActivityOpportunityDetailBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.activity_opportunity_detail.*
import kotlinx.android.synthetic.main.layout_add_visit_buttons.*


class OpportunityDetailActivity : BaseActivity<ActivityOpportunityDetailBinding>(), AddVisitButtonAdapter.OnClick {

    private val mViewModel: OpportunityDetailViewModel by lazy { ViewModelProvider(this).get(OpportunityDetailViewModel::class.java) }
    private var mAdapter: OpportunityDetailPagerAdapter? = null
    private var addVisitButtonAdapter: AddVisitButtonAdapter? = null

    companion object {
        const val PARAM_OPPORTUNITY_ID: String = "opportunity_id"
        const val PARAM_OPPORTUNITY_TITLE: String = "opportunity_name"
        const val PARAM_ACCOUNT_NAME: String = "account_name"
        const val PARAM_OPPORTUNITY_VALUE: String = "opportunity_value"
        private const val TWO = 2
        private const val SIX = 6
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
        mViewModel.opportunityName = intent.getStringExtra(PARAM_OPPORTUNITY_TITLE)
    }

    override fun initObserver() {
        val context: Context = this
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })
        mViewModel.addClicked.observe(this, Observer {

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
        mViewModel.addClicked.observe(this, Observer {
            layout_add_visit_button.visibility = when (layout_add_visit_button.visibility) {
                View.VISIBLE -> View.GONE
                else -> View.VISIBLE
            }
        })

        if (mViewModel.id > 0)
            mViewModel.getOpportunity(mViewModel.id)
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    override fun initEvent() {
        tl_opportunity.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { //do stuff here
                fab_add.visibility = when (tab.text) {
                    "Activities" -> View.VISIBLE
                    else -> View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initLayout() {
        mAdapter = OpportunityDetailPagerAdapter(supportFragmentManager, mViewModel)
        vp_opportunity.adapter = mAdapter

        addVisitButtonAdapter = AddVisitButtonAdapter(this)
        addVisitButtonAdapter?.setData(mViewModel.addVisitButtonList.value!!)

        val gridLayoutManager = GridLayoutManager(this, SIX)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                return TWO
            }
        }

        rv_add_visit_buttons.layoutManager = gridLayoutManager
        rv_add_visit_buttons.adapter = addVisitButtonAdapter
    }

    override fun onBackPressed() {
        when(layout_add_visit_button.visibility == View.VISIBLE){
            true-> layout_add_visit_button.visibility = View.GONE
            false -> super.onBackPressed()
        }
    }

    override fun onItemViewClicked(name: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}