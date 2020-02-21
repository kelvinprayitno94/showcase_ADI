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
        const val PARAM_ACCOUNT_NAME: String = "account_name"
        const val PARAM_OPPORTUNITY: String = "opportunity_name"
        const val PARAM_OPPORTUNITY_ENABLED: String = "opportunity_enabled"
        const val PARAM_ACTIVITY_DETAIL: String = "activity_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_appointment_detail)

        initData()
        initObserver()
        initViewModel()
        initEvent()
    }

    private fun initData() {
        mViewModel.opportunity = intent.getStringExtra(PARAM_OPPORTUNITY)

        val accountName = intent.getStringExtra(PARAM_ACCOUNT_NAME)
        if (accountName != null)
            mViewModel.accountName = accountName

        mViewModel.activityDetail = intent.getStringExtra(PARAM_ACTIVITY_DETAIL)
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

    override fun initEvent() {

    }
}