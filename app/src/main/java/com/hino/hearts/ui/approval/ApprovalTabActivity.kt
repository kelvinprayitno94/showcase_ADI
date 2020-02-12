package com.hino.hearts.ui.approval

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.hino.hearts.R
import com.hino.hearts.adapter.ApprovalCollapsingDocumentAdapter
import com.hino.hearts.adapter.ApprovalDocumentAdapter
import com.hino.hearts.databinding.ActivityApprovalTabBinding
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_approval_tab.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApprovalTabActivity : BaseActivity<ActivityApprovalTabBinding>() {

    private val viewModel by viewModel<ApprovalTabViewModel>()

    lateinit var approvalDocumentAdapter: ApprovalDocumentAdapter
    lateinit var approvalCollapsDocAdapter : ApprovalCollapsingDocumentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_approval_tab)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.documentLivedata.observe(this, Observer {
            val document = resources.getStringArray(R.array.approval_tab_document).toList()
            val branch = resources.getStringArray(R.array.approval_tab_branch).toList()

            for (index in document.indices){
                it.docList.add(ApprovalDocumentModel("PT. Dihardja", document[index], branch[index]))
                tab_layout_approval.addTab(tab_layout_approval.newTab().setText(document[index]))
            }

            it.docList[0].isSelected = true

            approvalDocumentAdapter = ApprovalDocumentAdapter(this, it, object: ApprovalDocumentAdapter.OnAdapterTap{
                override fun onTap(pos: Int) {

                }

            })

            approvalCollapsDocAdapter = ApprovalCollapsingDocumentAdapter(this, it, object: ApprovalCollapsingDocumentAdapter.OnAdapterTap{
                override fun onTap(pos: Int) {
                    if (!it.docList[pos].isSelected) {
                        it.docList[it.prevSelected].isSelected = false
                        it.docList[pos].isSelected = true
                        approvalCollapsDocAdapter.notifyItemChanged(it.prevSelected)
                        approvalCollapsDocAdapter.notifyItemChanged(pos)
                        tab_layout_approval.getTabAt(pos)?.select()
                    }
                }

            })

            tab_layout_approval.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    approvalDocumentAdapter.filter.filter(tab?.text)
                }

            })

            rv_approval.adapter = approvalDocumentAdapter
            rv_approval.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_approval.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))

            rv_collapsing_category_list.adapter = approvalCollapsDocAdapter
            rv_collapsing_category_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_collapsing_category_list.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))

        })
    }

    override fun initViewModel() {
        viewModel.getTabTitle()

    }

    override fun initEvent() {
        btn_expand_category.setOnClickListener {
            if (cl_collapsing_doc_type.visibility == View.VISIBLE){

                val rotate = RotateAnimation(
                    180f,
                    360f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                rotate.duration = 250
                rotate.interpolator = LinearInterpolator()
                rotate.fillAfter = true

                rotate.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        cl_collapsing_doc_type.visibility = View.INVISIBLE
                    }

                    override fun onAnimationStart(animation: Animation?) {

                    }

                })

                btn_expand_category.startAnimation(rotate)

            }else{
                val rotate = RotateAnimation(
                    0f,
                    180f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                rotate.duration = 250
                rotate.interpolator = LinearInterpolator()
                rotate.fillAfter = true

                rotate.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        cl_collapsing_doc_type.visibility = View.VISIBLE
                    }

                    override fun onAnimationStart(animation: Animation?) {

                    }

                })

                btn_expand_category.startAnimation(rotate)
            }
        }
    }

    fun initAdapter(){


    }
}
