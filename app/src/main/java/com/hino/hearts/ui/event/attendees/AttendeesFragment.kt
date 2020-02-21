package com.hino.hearts.ui.event.attendees


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.hino.hearts.R
import com.hino.hearts.adapter.EventAttendeesAdapter
import com.hino.hearts.databinding.FragmentAttendeesBinding
import com.hino.hearts.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_attendees.*
import kotlinx.android.synthetic.main.layout_toolbar_event.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class AttendeesFragment : BaseFragment<FragmentAttendeesBinding>() {

    private val viewModel by viewModel<AttendeesViewModel>()

    private lateinit var adapter: EventAttendeesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_attendees
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }

    private fun initLayout() {
        adapter = EventAttendeesAdapter()
        adapter.setData(viewModel.attendeesList.value!!)

        rv_event_attendees.layoutManager = LinearLayoutManager(context)
        rv_event_attendees.adapter = adapter
    }
}