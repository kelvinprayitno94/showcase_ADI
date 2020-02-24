package com.hino.hearts.ui.account.detail.contact.newContact

import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.databinding.ActivityNewContactBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailViewModel
import kotlinx.android.synthetic.main.activity_new_contact.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewContactActivity : BaseActivity<ActivityNewContactBinding>() {

    private val viewModel by viewModel<VehicleDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_new_contact)

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

        btn_save_new_contact.setOnClickListener {
            finish()
        }

    }

    private fun initAdapter() {

    }
}