package com.hino.hearts.ui.account.detail.vehicles

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountContactAdapter
import com.hino.hearts.adapter.AccountVehicleAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.model.AccountContactModel
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailActivity
import kotlinx.android.synthetic.main.fragment_account_detail_vehicles.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountVehicleFragment(var data: List<AccountListResponse.VehicleData>?) : BaseFragment<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountVehiclesViewModel>()

    lateinit var adapter: AccountVehicleAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_detail_vehicles
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        context?.let {

            adapter = AccountVehicleAdapter(it, data, object : AccountVehicleAdapter.OnAdapterTap {
                override fun onTap(pos: Int) {
                    startActivity<VehicleDetailActivity>(
                        "vehicle_data" to data?.get(pos)
                    )
                }

            })

            rv_vehicle.adapter = adapter
            rv_vehicle.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
        }
    }


}