package com.hino.hearts.ui.dragdrop

import android.os.Bundle
import androidx.core.view.marginEnd
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.adapter.DragDropListener
import com.hino.hearts.databinding.ActivityDragdropBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_dragdrop.*

class DragDropActivity : BaseActivity<ActivityDragdropBinding>() {

    private val mViewModel: DragDropViewModel by lazy { ViewModelProvider(this).get(DragDropViewModel::class.java) }
    private val mDragDropList: MutableList<DragDropList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_dragdrop)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel

        val marginStart:Int = resources.getDimension(R.dimen.dimens_15dp).toInt()
        val size = mViewModel.headers.size
        for (i in 0 until size) {
            val header = mViewModel.headers[i]
            val background: Int = when (mDragDropList.size % 2 == 0) {
                true -> R.drawable.shape_dragdrop_list_background
                false -> R.drawable.shape_dragdrop_list_background_darker
            }

            val marginEnd: Int = when (i == size-1) {
                true -> marginStart
                false -> 0
            }

            val dragDropList = DragDropList(this, header, background, marginStart, marginEnd, mViewModel.data[header]!!)
            mDragDropList.add(dragDropList)
            ll_dragdrop.addView(dragDropList)
        }
    }

    override fun initEvent() {
        v_left_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_LEFT))
        v_right_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_RIGHT))
    }
}
