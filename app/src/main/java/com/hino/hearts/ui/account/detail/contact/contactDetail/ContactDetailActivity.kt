package com.hino.hearts.ui.account.detail.contact.contactDetail

import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityContactDetailsBinding
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailViewModel
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

    }

    override fun initEvent() {

        main_toolbar.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {

    }
}