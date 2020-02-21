package com.hino.hearts.ui.opportunity.detail


import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.hino.hearts.R
import com.hino.hearts.adapter.AppointmentActivityAdapter
import com.hino.hearts.databinding.FragmentOpportunityActivitiesBinding
import com.hino.hearts.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_opportunity_activities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivitiesFragment : BaseFragment<FragmentOpportunityActivitiesBinding>() {

    private val mViewModel by viewModel<ActivitiesViewModel>()
    private var mAdapter: AppointmentActivityAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initLayout()
        initEvent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_opportunity_activities
    }

    override fun initObserver() {
        mViewModel.activityData.observe(this as LifecycleOwner, Observer {
            mAdapter?.setData(it)
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    fun initLayout() {
        mAdapter = AppointmentActivityAdapter()
        rv_appointment.layoutManager = LinearLayoutManager(context)
        rv_appointment.adapter = mAdapter
    }

    override fun initEvent() {
    }
}
