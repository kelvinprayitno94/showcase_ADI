package com.hino.hearts.ui.opportunity.appointment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityAppointmentDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_appointment_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AppointmentDetailActivity : BaseActivity<ActivityAppointmentDetailBinding>() {

    private val mViewModel: AppointmentDetailViewModel by lazy {
        ViewModelProvider(this).get(
            AppointmentDetailViewModel::class.java
        )
    }

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

        binding.viewModel = mViewModel

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
            mViewModel.accountLivedata.value = accountName

        mViewModel.activityDetail = intent.getStringExtra(PARAM_ACTIVITY_DETAIL)

        mViewModel.pageType = intent.getIntExtra(
            PARAM_PAGE_TYPE,
            PAGE_TYPE_APPOINTMENT
        )

        mViewModel.pageTitle = when (mViewModel.pageType) {
            PAGE_TYPE_APPOINTMENT -> R.string.appointment_details
            PAGE_TYPE_TASK -> R.string.task_details
            PAGE_TYPE_CALL_LOG -> R.string.call_log_details
            else -> R.string.appointment_details
        }

        mViewModel.opportunityEnabled = intent.getBooleanExtra(PARAM_OPPORTUNITY_ENABLED, false)
    }

    override fun initObserver() {
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
        fl_back.onClick{
            onBackPressed()
        }

        tv_acc_name.setOnClickListener {
            createPopupMenu(
                resources.getStringArray(R.array.account_visit_list).toList()
                )
        }
    }

    fun createPopupMenu(list: List<String>) {
        val menu = PopupMenu(this, tv_acc_name)

        for (i in list.iterator()) {
            menu.menu.add(i)
        }

        menu.gravity = Gravity.CENTER

        menu.setOnMenuItemClickListener {

            mViewModel.accountLivedata.value = it.title.toString()

            return@setOnMenuItemClickListener false
        }

        menu.show()
    }
}