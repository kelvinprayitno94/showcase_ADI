package com.hino.hearts.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityEventBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : BaseActivity<ActivityEventBinding>() {

    private val context = this

    private val viewModel by viewModel<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_event)
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {

    }
}
