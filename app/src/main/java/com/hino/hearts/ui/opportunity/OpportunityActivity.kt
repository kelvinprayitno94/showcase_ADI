package com.hino.hearts.ui.opportunity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hino.hearts.R
import com.hino.hearts.adapter.DragDropAdapter
import com.hino.hearts.adapter.DragDropListener
import com.hino.hearts.databinding.ActivityOpportunityBinding
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.opportunity.detail.OpportunityDetailActivity
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.activity_opportunity.*
import org.jetbrains.anko.startActivity

class OpportunityActivity : BaseActivity<ActivityOpportunityBinding>() {

    private val mViewModel: OpportunityViewModel by lazy { ViewModelProvider(this).get(OpportunityViewModel::class.java) }
    private val mDragDropList: MutableList<DragDropList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_opportunity)

        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
        val context: Context = this
        mViewModel.backClicked.observe(this, Observer {
            onBackPressed()
        })

        mViewModel.showLoading.observe(this, Observer {
            layout_custom_loading.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })
        mViewModel.errorBody.observe(this, Observer {
            NetworkManager.getInstance().handleResponse(context, it)
        })
        mViewModel.responseError.observe(this, Observer {
            NetworkManager.getInstance().handleErrorResponse(context, it)
        })
        mViewModel.data.observe(this, Observer {
            initList(mViewModel.headers, it)
        })
    }

    override fun initViewModel() {
        binding.viewModel = mViewModel
    }

    fun initList(headers: List<String>, data: HashMap<String, MutableList<OpportunityModel>>) {
        mDragDropList.clear()
        ll_dragdrop.removeAllViews()

        val size = headers.size
        for (i in 0 until size) {
            val header = headers[i]
            val background: Int = when (mDragDropList.size % 2 == 0) {
                true -> R.drawable.shape_dragdrop_list_background
                false -> R.drawable.shape_dragdrop_list_background_darker
            }

            val dragDropList = DragDropList(
                this,
                header,
                background,
                object : DragDropAdapter.ClickListener {
                    override fun onItemClicked(item: OpportunityModel) {
                        startActivity<OpportunityDetailActivity>(
                            OpportunityDetailActivity.PARAM_OPPORTUNITY_ID to item.id,
                            OpportunityDetailActivity.PARAM_OPPORTUNITY_TITLE to item.opportunityName
                        )
                    }
                },
                data[header]!!
            )

            mDragDropList.add(dragDropList)
            ll_dragdrop.addView(dragDropList)
        }
    }

    override fun initEvent() {
        v_left_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_LEFT))
        v_right_area.setOnDragListener(DragDropListener(hs_dragdrop, DragDropListener.DIRECTION_RIGHT))
    }
}
