package com.hino.hearts.ui.opportunity.detail

import android.content.Intent
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
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.opportunity.appointment.AppointmentDetailActivity
import kotlinx.android.synthetic.main.activity_opportunity_detail.*
import kotlinx.android.synthetic.main.layout_add_visit_buttons.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivityForResult
import java.io.Serializable


class OpportunityDetailActivity : BaseActivity<ActivityOpportunityDetailBinding>() {

    private val mViewModel: OpportunityDetailViewModel by lazy { ViewModelProvider(this).get(OpportunityDetailViewModel::class.java) }
    private var mAdapter: OpportunityDetailPagerAdapter? = null
    private var addVisitButtonAdapter: AddVisitButtonAdapter? = null

    companion object {
        const val PARAM_OPPORTUNITY_ID: String = "opportunity_id"
        const val PARAM_OPPORTUNITY_NAME: String = "opportunity_name"
        const val PARAM_ACCOUNT_ID: String = "account_id"
        const val PARAM_ACCOUNT_NAME: String = "account_name"
        const val PARAM_OPPORTUNITY_VALUE: String = "opportunity_value"
        const val PARAM_OPPORTUNITY_OBJECT: String = "opportunity_object"

        private const val TWO = 2
        private const val SIX = 6
        private const val ACTION_CREATE_VISIT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_opportunity_detail)

        initObserver()
        initViewModel()
        initLayout()
        initEvent()
    }

    override fun initObserver() {

        mViewModel.addClicked.observe(this, Observer {
            layout_add_visit_button.visibility = when (layout_add_visit_button.visibility) {
                View.VISIBLE -> View.GONE
                else -> View.VISIBLE
            }
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        val fromAccount = intent.getBooleanExtra("is_from_account", false)

        mViewModel.opportunityId = intent.getIntExtra(PARAM_OPPORTUNITY_ID, 0)
        mViewModel.opportunityName = intent.getStringExtra(PARAM_OPPORTUNITY_NAME)

        mViewModel.accountId = intent.getIntExtra(PARAM_ACCOUNT_ID, 0)
        val accountName = intent.getStringExtra(PARAM_ACCOUNT_NAME)
        if (accountName != null)
            mViewModel.accountName = accountName

        mViewModel.opportunityValue = intent.getLongExtra(PARAM_OPPORTUNITY_VALUE, 0)

        if (!fromAccount){
            val serializable: Serializable? = intent.getSerializableExtra(PARAM_OPPORTUNITY_OBJECT)
            mViewModel.opportunity = when (serializable != null) {
                true -> serializable as OpportunityModel
                false -> null
            }
        }
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

        fl_back.onClick{
            onBackPressed()
        }
    }

    private fun initLayout() {
        mAdapter = OpportunityDetailPagerAdapter(supportFragmentManager, mViewModel.opportunityId)
        vp_opportunity.adapter = mAdapter

        addVisitButtonAdapter = AddVisitButtonAdapter(object: AddVisitButtonAdapter.OnClick {
            override fun onItemViewClicked(name: Int) {
                layout_add_visit_button.visibility = View.GONE

                when (name) {
                    R.string.appointment -> startActivityForResult<AppointmentDetailActivity>(requestCode = ACTION_CREATE_VISIT,
                        params = *arrayOf(
                        AppointmentDetailActivity.PARAM_PAGE_TYPE to AppointmentDetailActivity.PAGE_TYPE_APPOINTMENT,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_ID to mViewModel.opportunityId,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_NAME to mViewModel.opportunityName,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_ID to mViewModel.accountId,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_NAME to mViewModel.accountName,
                        AppointmentDetailActivity.PARAM_LOCK_ACCOUNT_AND_OPPORTUNITY to true))

                    R.string.task -> startActivityForResult<AppointmentDetailActivity>(requestCode = ACTION_CREATE_VISIT,
                        params = *arrayOf(
                        AppointmentDetailActivity.PARAM_PAGE_TYPE to AppointmentDetailActivity.PAGE_TYPE_TASK,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_ID to mViewModel.opportunityId,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_NAME to mViewModel.opportunityName,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_ID to mViewModel.accountId,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_NAME to mViewModel.accountName,
                        AppointmentDetailActivity.PARAM_LOCK_ACCOUNT_AND_OPPORTUNITY to true))

                    R.string.call_log -> startActivityForResult<AppointmentDetailActivity>(requestCode = ACTION_CREATE_VISIT,
                        params = *arrayOf(
                        AppointmentDetailActivity.PARAM_PAGE_TYPE to AppointmentDetailActivity.PAGE_TYPE_CALL_LOG,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_ID to mViewModel.opportunityId,
                        AppointmentDetailActivity.PARAM_SELECTED_OPPORTUNITY_NAME to mViewModel.opportunityName,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_ID to mViewModel.accountId,
                        AppointmentDetailActivity.PARAM_SELECTED_ACCOUNT_NAME to mViewModel.accountName,
                        AppointmentDetailActivity.PARAM_LOCK_ACCOUNT_AND_OPPORTUNITY to true))
                }
            }
        })
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            ACTION_CREATE_VISIT -> mAdapter?.refreshActivity()
        }
    }
}