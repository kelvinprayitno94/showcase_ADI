package com.hino.hearts.ui.approval.category

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.tabs.TabLayout
import com.hino.hearts.R
import com.hino.hearts.adapter.ApprovalCollapsingDocumentAdapter
import com.hino.hearts.adapter.ApprovalDocTypeFilterAdapter
import com.hino.hearts.adapter.ApprovalDocumentAdapter
import com.hino.hearts.databinding.ActivityApprovalTabBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.detail.ApprovalDetailActivity
import com.hino.hearts.ui.approval.filter.ApprovalFilterActivity
import com.hino.hearts.util.DividerItemDecoration
import com.hino.hearts.util.UserDefaults
import kotlinx.android.synthetic.main.activity_approval_tab.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApprovalTabActivity : BaseActivity<ActivityApprovalTabBinding>() {

    private val viewModel by viewModel<ApprovalTabViewModel>()

    lateinit var approvalDocumentAdapter: ApprovalDocumentAdapter
    lateinit var approvalCollapsDocAdapter: ApprovalCollapsingDocumentAdapter
    lateinit var approvalDocTypeFilterAdapter: ApprovalDocTypeFilterAdapter

    var open = true

    var selectedIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_approval_tab)



        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.approvalListLiveData.observe(this, Observer {

            var docType = resources.getStringArray(R.array.approval_tab_document).toList()

            var docList = it.listData

            for (i in docType) {
                tab_layout_approval.addTab(tab_layout_approval.newTab().setText(i))
            }

            val roleid = UserDefaults.getInstance().getInt(UserDefaults.USER_ROLE_ID, 1)

            approvalDocumentAdapter =
                ApprovalDocumentAdapter(
                    this,
                    docList,
                    roleid,
                    object : ApprovalDocumentAdapter.OnAdapterTap {
                        override fun onTap(pos: Int) {

                            val intent = Intent(
                                this@ApprovalTabActivity,
                                ApprovalDetailActivity::class.java
                            )

                            intent.putExtra("data", docList[pos])

                            startActivity(intent)
                        }

                    })

            approvalCollapsDocAdapter = ApprovalCollapsingDocumentAdapter(
                this,
                it,
                docType,
                selectedIndex,
                object : ApprovalCollapsingDocumentAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
//                        if (!it.docList[pos].isSelected) {
//                            it.docList[it.prevSelected].isSelected = false
//                            it.docList[pos].isSelected = true
                        it.selected = pos
                        approvalCollapsDocAdapter.notifyDataSetChanged()
                        tab_layout_approval.getTabAt(pos)?.select()
                        btn_expand_category.callOnClick()
//                        approvalDocTypeFilterAdapter.notifyDataSetChanged()
//                            it.prevSelected = pos
//                            tab_layout_approval.getTabAt(pos)?.select()
//                            approvalDocumentAdapter.filter.filter(it.docList[pos].documentType)
//                        }
                    }

                })

            approvalDocTypeFilterAdapter = ApprovalDocTypeFilterAdapter(this, it,
                selectedIndex,
                object : ApprovalDocTypeFilterAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
//                        if (!it.docList[pos].isSelected) {
//                            it.docList[it.prevSelected].isSelected = false
//                            it.docList[pos].isSelected = true
                        it.selected = pos
                        approvalDocTypeFilterAdapter.notifyDataSetChanged()
                        approvalCollapsDocAdapter.notifyDataSetChanged()
//                            it.prevSelected = pos
//                            approvalDocumentAdapter.filter.filter(it.docList[pos].documentType)
//                        }
                    }

                })

            tab_layout_approval.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let { _it ->
                        it.selected = _it.position
                        approvalCollapsDocAdapter.notifyDataSetChanged()
                    }
//                    approvalDocumentAdapter.filter.filter(tab?.text)
                }

            })

            rv_approval.adapter = approvalDocumentAdapter
            rv_approval.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_approval.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getDrawable(
                        this@ApprovalTabActivity,
                        R.drawable.divider
                    )
                )
            )

            rv_collapsing_category_list.adapter = approvalCollapsDocAdapter
            rv_collapsing_category_list.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_collapsing_category_list.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getDrawable(this@ApprovalTabActivity, R.drawable.divider)
                )
            )

            rv_tab_layout_approval_doc_type.adapter = approvalDocTypeFilterAdapter
            rv_tab_layout_approval_doc_type.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rv_tab_layout_approval_doc_type)

        })

        viewModel.showCateTextLiveData.observe(this, Observer {
            animate(it)
        })

        viewModel.animateArrorLiveData.observe(this, Observer {


            val rotate = RotateAnimation(
                it.first,
                it.second,
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

                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })

            btn_expand_category.startAnimation(rotate)
        })
    }

    fun animate(flag: Boolean) {

        //show
        if (flag) {

            val animSlideDown =
                loadAnimation(applicationContext, R.anim.slide_in_from_top)
            animSlideDown.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    open = true
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
//            animSlideDown.fillAfter = true
            cl_collapsing_doc_type.startAnimation(animSlideDown)

        } else {

            val animSlideDown =
                loadAnimation(applicationContext, R.anim.slide_out_to_top)
            animSlideDown.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    open = false
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
//            animSlideDown.fillAfter = true
            cl_collapsing_doc_type.startAnimation(animSlideDown)
        }

    }

    override fun initViewModel() {
        viewModel.getApproval()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.approval_filter -> {
                startActivity(
                    Intent(
                        this@ApprovalTabActivity,
                        ApprovalFilterActivity::class.java
                    )
                )
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.approval_menu, menu)
        return true
    }

    override fun initEvent() {

        setSupportActionBar(main_toolbar)

        btn_expand_category.setOnClickListener {

            viewModel.showDocTypeSlidingView(
                cl_collapsing_doc_type.visibility
            )

        }

        main_toolbar.setOnClickListener {
            finish()
        }
    }

    fun initAdapter() {


    }
}