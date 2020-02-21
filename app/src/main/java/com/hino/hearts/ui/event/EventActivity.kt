package com.hino.hearts.ui.event

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hino.hearts.R
import com.hino.hearts.adapter.EventAdapter
import com.hino.hearts.databinding.ActivityEventBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import kotlinx.android.synthetic.main.layout_toolbar_back.tb_back
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : BaseActivity<ActivityEventBinding>() {

    private val context = this

    private val viewModel by viewModel<EventViewModel>()

    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_event)

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

        initEventList()
    }

    private fun initEventList(){
        adapter = EventAdapter()
        adapter.setData(viewModel.eventList.value!!)

        rv_events.layoutManager = LinearLayoutManager(context)
        rv_events.adapter = adapter
    }
}
