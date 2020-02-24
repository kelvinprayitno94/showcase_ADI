package com.hino.hearts.ui.appointment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityAppointmentDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_appointment_detail.*

class AppointmentDetailActivity : BaseActivity<ActivityAppointmentDetailBinding>() {

    private val mViewModel: AppointmentDetailViewModel by lazy { ViewModelProvider(this).get(AppointmentDetailViewModel::class.java) }

    companion object {
        const val PARAM_PAGE_TYPE: String = "page_type"
        const val PARAM_ACCOUNT_NAME: String = "account_name"
        const val PARAM_OPPORTUNITY: String = "opportunity_name"
        const val PARAM_OPPORTUNITY_ENABLED: String = "opportunity_enabled"
        const val PARAM_ACTIVITY_DETAIL: String = "activity_detail"

        const val PAGE_TYPE_APPOINTMENT: Int = 0
        const val PAGE_TYPE_TASK: Int = 1
        const val PAGE_TYPE_CALL_LOG: Int = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_appointment_detail)

        initData()
        initObserver()
        initViewModel()
        initLayout()
        initEvent()
    }

    private fun initData() {
        mViewModel.opportunity = intent.getStringExtra(PARAM_OPPORTUNITY)

        val accountName = intent.getStringExtra(PARAM_ACCOUNT_NAME)
        if (accountName != null)
            mViewModel.accountName = accountName

        mViewModel.activityDetail = intent.getStringExtra(PARAM_ACTIVITY_DETAIL)
        mViewModel.pageType = intent.getIntExtra(PARAM_PAGE_TYPE, PAGE_TYPE_APPOINTMENT)
        mViewModel.pageTitle = when(mViewModel.pageType) {
            PAGE_TYPE_APPOINTMENT -> R.string.appointment_details
            PAGE_TYPE_TASK -> R.string.task_details
            PAGE_TYPE_CALL_LOG -> R.string.call_log_details
            else -> R.string.appointment_details
        }
    }

    override fun initObserver() {
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })
        mViewModel.saveClicked.observe(this, Observer {
            //TODO: Do something
            finish()
        })

        /*val context: Context = this
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
        })*/

        /*if (mViewModel.id > 0)
            mViewModel.getAppointment(mViewModel.id)*/
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    private fun initLayout() {
        tv_appointment_title.setText(mViewModel.pageTitle)
    }

    override fun initEvent() {

    }
}