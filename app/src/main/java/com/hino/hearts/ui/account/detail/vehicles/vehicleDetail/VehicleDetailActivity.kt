package com.hino.hearts.ui.account.detail.vehicles.vehicleDetail

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.databinding.ActivityVehicleDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_vehicle_detail.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleDetailActivity : BaseActivity<ActivityVehicleDetailBinding>() {

    private val viewModel by viewModel<VehicleDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_vehicle_detail)
        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.showPopupMenuLiveData.observe(this, Observer {
            createPopupMenu(it, tv_category, 0)
        })

        viewModel.showTypePopupMenuLiveData.observe(this, Observer {
            createPopupMenu(it, tv_type_unit, 1)
        })

    }

    override fun initViewModel() {

    }

    override fun initEvent() {
        main_toolbar.setOnClickListener {
            finish()
        }

        btn_save_vehicle.setOnClickListener {
            finish()
        }

        iv_vehicle_cate_dropdown.setOnClickListener {
            viewModel.showCategoryPopupMenu(resources.getStringArray(R.array.vehicle_category_list).toList())
        }

        iv_vehicle_type_unit_dropdown.setOnClickListener {
            viewModel.showTypeUnitPopupMenu(resources.getStringArray(R.array.vehicle_type_unit_list).toList())

        }

    }

    private fun initAdapter() {

    }

    fun createPopupMenu(list: List<String>, view: View, flag: Int) {
        val menu = PopupMenu(this, view)

        for (i in list.iterator()){
            menu.menu.add(i)
        }

        menu.gravity = Gravity.CENTER

        menu.setOnMenuItemClickListener {

            when (flag) {
                0 -> {
                    viewModel.categoryLiveData.value = it.title.toString()
                }
                else -> {
                    viewModel.typeLiveData.value = it.title.toString()
                }

            }

            return@setOnMenuItemClickListener false
        }

        menu.show()
    }
}