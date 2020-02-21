package com.hino.hearts.ui.notification

import android.os.Bundle
import androidx.lifecycle.Observer
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityNotificationDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_notification_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationDetailActivity : BaseActivity<ActivityNotificationDetailBinding>() {

    private val context = this

    private val viewModel by viewModel<NotificationDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_notification_detail)

        initObserver()
        initViewModel()
        initEvent()
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
}
