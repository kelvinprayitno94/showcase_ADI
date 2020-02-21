package com.hino.hearts.ui.account.detail.vehicles

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountContactAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.model.AccountContactModel
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailActivity
import kotlinx.android.synthetic.main.fragment_account_detail_vehicles.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountVehicleFragment : BaseFragment<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountVehiclesViewModel>()

    lateinit var adapter: AccountContactAdapter

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

            val item: MutableList<AccountContactModel> = ArrayList()

            for (i in 0 until 6) {
                item.add(AccountContactModel("FJ 190 JP", "#123123"))
            }

            adapter = AccountContactAdapter(it, item, object : AccountContactAdapter.OnAdapterTap {
                override fun onTap(pos: Int) {
                    startActivity(Intent(activity, VehicleDetailActivity::class.java))
                }

            })

            rv_vehicle.adapter = adapter
            rv_vehicle.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
        }
    }


}