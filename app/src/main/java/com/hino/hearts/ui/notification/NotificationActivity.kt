package com.hino.hearts.ui.notification

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hino.hearts.R
import com.hino.hearts.adapter.NotificationAdapter
import com.hino.hearts.databinding.ActivityNotificationBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import kotlinx.android.synthetic.main.layout_toolbar_back.tb_back
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationActivity : BaseActivity<ActivityNotificationBinding>() {

    private val context = this

    private val viewModel by viewModel<NotificationViewModel>()

    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_notification)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        fl_back.onClick{
            onBackPressed()
        }
    }

    private fun initLayout(){
        setSupportActionBar(tb_back)

        initNotificationList()
    }

    private fun initNotificationList(){
        adapter = NotificationAdapter()
        adapter.setData(viewModel.notificationList.value!!)

        rv_notification.layoutManager = LinearLayoutManager(context)
        rv_notification.adapter = adapter
    }
}
