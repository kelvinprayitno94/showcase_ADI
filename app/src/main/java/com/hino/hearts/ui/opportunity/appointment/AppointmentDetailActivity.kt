package com.hino.hearts.ui.opportunity.appointment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityAppointmentDetailBinding
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.NetworkManager
import com.hino.hearts.util.UserDefaults
import kotlinx.android.synthetic.main.activity_appointment_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.alert

class AppointmentDetailActivity : BaseActivity<ActivityAppointmentDetailBinding>() {

    private val mViewModel: AppointmentDetailViewModel by lazy {
        ViewModelProvider(this).get(
            AppointmentDetailViewModel::class.java
        )
    }

    companion object {
        const val PARAM_PAGE_TYPE: String = "page_type"
        const val PARAM_SELECTED_ACCOUNT_ID: String = "selected_account_id"
        const val PARAM_SELECTED_ACCOUNT_NAME: String = "selected_account_name"
        const val PARAM_SELECTED_OPPORTUNITY_ID: String = "selected_opportunity_id"
        const val PARAM_SELECTED_OPPORTUNITY_NAME: String = "selected_opportunity_name"
        const val PARAM_ACTIVITY_DETAIL: String = "activity_detail"
        const val PARAM_LOCK_ACCOUNT_AND_OPPORTUNITY: String = "lock_account_opportunity"
        const val PARAM_IS_READ_ONLY: String = "is_readonly"

        const val PAGE_TYPE_APPOINTMENT: Int = 0
        const val PAGE_TYPE_TASK: Int = 1
        const val PAGE_TYPE_CALL_LOG: Int = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_appointment_detail)

        initObserver()
        initViewModel()
        initLayout()
        initEvent()
    }

    override fun initObserver() {
        val context: Context = this
        mViewModel.saveClicked.observe(this, Observer {
            if (mViewModel.selectedAccountId.value == -1) {
                alert(getString(mViewModel.pageTitle), "Account must not be empty")
                return@Observer
            }

            if (mViewModel.selectedOpportunityId.value == -1) {
                alert(getString(mViewModel.pageTitle), "Opportunity must not be empty")
                return@Observer
            }

            if (tv_activity.text!!.toString().trim() == "") {
                alert(getString(mViewModel.pageTitle), "Activity Details must not be empty")
                return@Observer
            }

            mViewModel.activityDetail.value = tv_activity.text!!.toString().trim()
            val type = when (mViewModel.pageType) {
                PAGE_TYPE_APPOINTMENT -> "Appointment"
                PAGE_TYPE_TASK -> "Task"
                PAGE_TYPE_CALL_LOG -> "CallLog"
                else -> "Appointment"
            }
            mViewModel.createVisit(UserDefaults.getInstance().getInt(UserDefaults.USER_ID, 0), mViewModel.selectedOpportunityId.value!!, mViewModel.selectedAccountId.value!!, mViewModel.activityDetail.value!!, type)
        })
        mViewModel.createVisitResponse.observe(this, Observer {
            if (it) {
                setResult(Activity.RESULT_OK)
                Toast.makeText(context, "Create " + getString(mViewModel.pageTitle) + " Success", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(context, "Failed to create " + getString(mViewModel.pageTitle), Toast.LENGTH_SHORT).show()
            }
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
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        mViewModel.pageType = intent.getIntExtra(PARAM_PAGE_TYPE, PAGE_TYPE_APPOINTMENT)
        mViewModel.pageTitle = when (mViewModel.pageType) {
            PAGE_TYPE_APPOINTMENT -> R.string.appointment_details
            PAGE_TYPE_TASK -> R.string.task_details
            PAGE_TYPE_CALL_LOG -> R.string.call_log_details
            else -> R.string.appointment_details
        }

        mViewModel.selectedAccountId.value = intent.getIntExtra(PARAM_SELECTED_ACCOUNT_ID, -1)
        if (mViewModel.selectedAccountId.value != -1)
            mViewModel.selectedAccountName.value = intent.getStringExtra(PARAM_SELECTED_ACCOUNT_NAME)!!

        mViewModel.selectedOpportunityId.value = intent.getIntExtra(PARAM_SELECTED_OPPORTUNITY_ID, -1)
        if (mViewModel.selectedOpportunityId.value != -1)
            mViewModel.selectedOpportunityName.value = intent.getStringExtra(PARAM_SELECTED_OPPORTUNITY_NAME)!!

        mViewModel.activityDetail.value = intent.getStringExtra(PARAM_ACTIVITY_DETAIL)

        mViewModel.lockAccountOpportunity = intent.getBooleanExtra(PARAM_LOCK_ACCOUNT_AND_OPPORTUNITY, false)
        mViewModel.isReadOnlyMode = intent.getBooleanExtra(PARAM_IS_READ_ONLY, false)
        if (!mViewModel.isReadOnlyMode)
            mViewModel.fetchAccountList()
    }

    private fun initLayout() {
        tv_appointment_title.setText(mViewModel.pageTitle)
    }

    override fun initEvent() {
        fl_back.onClick{
            onBackPressed()
        }

        tv_acc_name.setOnClickListener {
            if (mViewModel.documentLivedata.value != null)
                createAccountPopupMenu(mViewModel.documentLivedata.value!!.listData)
        }

        tv_opportunity_field.setOnClickListener {
            if (mViewModel.selectedAccountListData != null)
                createOpportunityPopupMenu(mViewModel.selectedAccountListData!!.opportunity!!)
        }
    }

    private fun createAccountPopupMenu(list: List<AccountListResponse.AccListData>) {
        val menu = PopupMenu(this, tv_acc_name)

        for (accListData in list.iterator()) {
            menu.menu.add(accListData.account!!.accountName)
        }

        menu.gravity = Gravity.CENTER
        menu.setOnMenuItemClickListener {
            val selectedItem = list.find { data -> data.account!!.accountName == it.title.toString() }
            if (selectedItem != null) {
                //Clear if different
                if (selectedItem != mViewModel.selectedAccountListData) {
                    mViewModel.selectedOpportunityId.value = -1
                    mViewModel.selectedOpportunityName.value = "Opportunity"
                }

                mViewModel.selectedAccountListData = selectedItem
                mViewModel.selectedAccountId.value = selectedItem.account!!.id
                mViewModel.selectedAccountName.value = selectedItem.account!!.accountName!!
            }

            return@setOnMenuItemClickListener false
        }

        menu.show()
    }

    private fun createOpportunityPopupMenu(list: List<AccountListResponse.OpportunityData>) {
        val menu = PopupMenu(this, tv_opportunity_field)

        for (opportunity in list.iterator()) {
            menu.menu.add(opportunity.opportunityName)
        }

        menu.gravity = Gravity.CENTER
        menu.setOnMenuItemClickListener {
            val selectedItem = list.find { data -> data.opportunityName == it.title.toString() }
            if (selectedItem != null) {
                mViewModel.selectedOpportunityId.value = selectedItem.id
                mViewModel.selectedOpportunityName.value = selectedItem.opportunityName!!
            }

            return@setOnMenuItemClickListener false
        }

        menu.show()
    }
}