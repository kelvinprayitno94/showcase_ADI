package com.hino.hearts.ui.account.detail.vehicles.vehicleDetail

import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.databinding.ActivityVehicleDetailBinding
import com.hino.hearts.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleDetailActivity : BaseActivity<ActivityVehicleDetailBinding>() {

    private val viewModel by viewModel<VehicleDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        setBinding(R.layout.activity_vehicle_detail)

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


    }

    private fun initAdapter() {

    }
}