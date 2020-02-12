package com.hino.hearts.ui.dragdrop

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityDragdropBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_dragdrop.*

class DragDropActivity : BaseActivity<ActivityDragdropBinding>() {

    private val _viewModel: DragDropViewModel by lazy { ViewModelProvider(this).get(DragDropViewModel::class.java) }
    private val _dragDropList: MutableList<DragDropList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_dragdrop)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = _viewModel

        for (header in _viewModel.headers) {
            val dragDropList = DragDropList(this, header, _viewModel.data[header]!!)
            _dragDropList.add(dragDropList)

            ll_dragdrop.addView(dragDropList)
        }
    }

    override fun initEvent() {

    }
}
