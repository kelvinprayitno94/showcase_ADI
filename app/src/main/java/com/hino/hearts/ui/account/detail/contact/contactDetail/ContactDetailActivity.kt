package com.hino.hearts.ui.account.detail.contact.contactDetail

import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityContactDetailsBinding
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailViewModel
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailActivity : BaseActivity<ActivityContactDetailsBinding>() {

    private val viewModel by viewModel<VehicleDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_contact_details)

        super.onCreate(savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        var data: AccountListResponse.ContactData? = intent.getParcelableExtra("contact_data")

        data?.run {
            tv_contact_name.setText(name)
            tv_phone_no.setText(phoneNumber)
        }
    }

    override fun initEvent() {

        main_toolbar.setOnClickListener {
            finish()
        }

        btn_save_contact.setOnClickListener {
            finish()
        }

        btn_delete_contact.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {

    }
}