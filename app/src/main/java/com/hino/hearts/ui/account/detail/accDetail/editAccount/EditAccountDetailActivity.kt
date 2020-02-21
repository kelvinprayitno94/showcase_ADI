package com.hino.hearts.ui.account.detail.accDetail.editAccount

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEditAccountDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_account_detail.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditAccountDetailActivity : BaseActivity<ActivityEditAccountDetailBinding>() {

    private val viewModel by viewModel<EditAccountDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_edit_account_detail)

        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {

        viewModel.showCityPopupMenuLiveData.observe(this, Observer {
            createPopupMenu(it, tl_business_field, 0)
        })

        viewModel.showPopupMenuLiveData.observe(this, Observer {
            createPopupMenu(it, tl_city, 1)
        })

    }

    override fun initViewModel() {

    }

    override fun initEvent() {
        tl_business_field.setOnClickListener {
            viewModel.showBusinessPopupMenu(resources.getStringArray(R.array.business_field_list).toList())
        }

        tl_city.setOnClickListener {
            viewModel.showCityPopupMenu(resources.getStringArray(R.array.city_list).toList())
        }

        main_toolbar.setOnClickListener {
            finish()
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
                    viewModel.setCityText(it.title.toString())
                }
                else -> {
                    viewModel.setBusinessFieldText(it.title.toString())
                }

            }

            return@setOnMenuItemClickListener false
        }

        menu.show()
    }
}