package com.hino.hearts.ui.opportunity.detail.activities


import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.hino.hearts.R
import com.hino.hearts.adapter.OpportunityVisitAdapter
import com.hino.hearts.databinding.FragmentOpportunityActivitiesBinding
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.fragment_opportunity_activities.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.textColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivitiesFragment(opportunityId: Int) : BaseFragment<FragmentOpportunityActivitiesBinding>() {

    private val mViewModel by viewModel<ActivitiesViewModel>()
    private val mOpportunityId = opportunityId
    private var mAdapter: OpportunityVisitAdapter? = null

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
        val owner: LifecycleOwner = this
        mViewModel.showLoading.observe(owner, Observer {
            layout_custom_loading.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })
        mViewModel.errorBody.observe(owner, Observer {
            NetworkManager.getInstance().handleResponse(context, it)
        })
        mViewModel.responseError.observe(owner, Observer {
            NetworkManager.getInstance().handleErrorResponse(context, it)
        })
        mViewModel.activityData.observe(owner, Observer {
            mAdapter?.setData(it)
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        mViewModel.opportunityId = mOpportunityId

        if (mViewModel.opportunityId > 0)
            mViewModel.getOpportunity(mViewModel.opportunityId)
    }

    fun initLayout() {
        mAdapter = OpportunityVisitAdapter()
        rv_appointment.layoutManager = LinearLayoutManager(context)
        rv_appointment.adapter = mAdapter
    }

    override fun initEvent() {
        btn_appointment.onClick {
            changeVisitType("Appointment")
        }

        btn_task.onClick {
            changeVisitType("Task")
        }

        btn_calllog.onClick {
            changeVisitType("CallLog")
        }
    }

    private fun changeVisitType(type: String){
        when(type){
            "Appointment"->{
                btn_appointment.background.setTint(ContextCompat.getColor(this.context!!, R.color.red))
                btn_appointment.textColor = ContextCompat.getColor(this.context!!, R.color.white)

                btn_task.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_task.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)

                btn_calllog.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_calllog.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)
            }
            "Task"->{
                btn_appointment.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_appointment.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)

                btn_task.background.setTint(ContextCompat.getColor(this.context!!, R.color.red))
                btn_task.textColor = ContextCompat.getColor(this.context!!, R.color.white)

                btn_calllog.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_calllog.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)
            }
            "CallLog"->{
                btn_appointment.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_appointment.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)

                btn_task.background.setTint(ContextCompat.getColor(this.context!!, R.color.white))
                btn_task.textColor = ContextCompat.getColor(this.context!!, R.color.grey4a)

                btn_calllog.background.setTint(ContextCompat.getColor(this.context!!, R.color.red))
                btn_calllog.textColor = ContextCompat.getColor(this.context!!, R.color.white)
            }
        }

        mViewModel.changeVisitType(type)
    }

    fun refresh() {
        if (mViewModel.opportunityId > 0)
            mViewModel.getOpportunity(mViewModel.opportunityId)
    }
}
