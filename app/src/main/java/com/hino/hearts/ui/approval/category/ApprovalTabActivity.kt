package com.hino.hearts.ui.approval.category

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.*
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
import com.hino.hearts.model.ApprovalDocumentModel
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.approval.detail.ApprovalDetailActivity
import com.hino.hearts.ui.approval.filter.ApprovalFilterActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_approval_tab.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class ApprovalTabActivity : BaseActivity<ActivityApprovalTabBinding>() {

    private val viewModel by viewModel<ApprovalTabViewModel>()

    lateinit var approvalDocumentAdapter: ApprovalDocumentAdapter
    lateinit var approvalCollapsDocAdapter: ApprovalCollapsingDocumentAdapter
    lateinit var approvalDocTypeFilterAdapter: ApprovalDocTypeFilterAdapter

    var open = true

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
        viewModel.documentLivedata.observe(this, Observer {
            val document = resources.getStringArray(R.array.approval_tab_document).toList()
            val branch = resources.getStringArray(R.array.approval_tab_branch).toList()

            for (index in document.indices) {
                it.docList.add(
                    ApprovalDocumentModel(
                        "PT. Dihardja",
                        document[index],
                        branch[index]
                    )
                )
                val newTab = tab_layout_approval.newTab()
                newTab.text = document[index]
                tab_layout_approval.addTab(newTab)
            }

            it.docList[0].isSelected = true

            approvalDocumentAdapter =
                ApprovalDocumentAdapter(this, it, object : ApprovalDocumentAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
                        startActivity(
                            Intent(
                                this@ApprovalTabActivity,
                                ApprovalDetailActivity::class.java
                            )
                        )
                    }

                })

            approvalCollapsDocAdapter = ApprovalCollapsingDocumentAdapter(
                this,
                it,
                object : ApprovalCollapsingDocumentAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
                        if (!it.docList[pos].isSelected) {
                            it.docList[it.prevSelected].isSelected = false
                            it.docList[pos].isSelected = true
                            approvalCollapsDocAdapter.notifyDataSetChanged()
                            it.prevSelected = pos
//                            tab_layout_approval.getTabAt(pos)?.select()
                            approvalDocumentAdapter.filter.filter(it.docList[pos].documentType)
                        }
                    }

                })

            approvalDocTypeFilterAdapter = ApprovalDocTypeFilterAdapter(this, it,
                object : ApprovalDocTypeFilterAdapter.OnAdapterTap {
                    override fun onTap(pos: Int) {
                        if (!it.docList[pos].isSelected) {
                            it.docList[it.prevSelected].isSelected = false
                            it.docList[pos].isSelected = true
                            approvalDocTypeFilterAdapter.notifyDataSetChanged()
                            it.prevSelected = pos
                            approvalDocumentAdapter.filter.filter(it.docList[pos].documentType)
                        }
                    }

                })

            tab_layout_approval.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
            rv_collapsing_category_list.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rv_collapsing_category_list.addItemDecoration(
                DividerItemDecoration(
                    resources.getDrawable(
                        R.drawable.divider
                    )
                )
            )

            rv_tab_layout_approval_doc_type.adapter = approvalDocTypeFilterAdapter
            rv_tab_layout_approval_doc_type.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rv_tab_layout_approval_doc_type)

        })

        viewModel.showCateTextLiveData.observe(this, Observer {
            //            animate(it)
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
//            val animation: Animation = TranslateAnimation(
//                0f, 0f, -100f, 0f
//            )
//            animation.duration = 250
            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_top)
            anim.fillAfter = true
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
//                    cl_collapsing_doc_type.visibility = View.INVISIBLE
                    open = false
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })

//            val animate = TranslateAnimation(
//                0f,  // fromXDelta
//                0f,  // toXDelta
//                cl_collapsing_doc_type.height.toFloat(),  // fromYDelta
//                0f
//            ) // toYDelta
//            animate.setAnimationListener(object : Animation.AnimationListener {
//                override fun onAnimationRepeat(animation: Animation?) {
//
//                }
//
//                override fun onAnimationEnd(animation: Animation?) {
////                    cl_collapsing_doc_type.visibility = View.INVISIBLE
//                    open = true
//                }
//
//                override fun onAnimationStart(animation: Animation?) {
//                }
//
//            })
//
//            animate.repeatMode = Animation.REVERSE
//            animate.duration = 500
//            animate.fillAfter = true
//            animate.interpolator = ReverseInterpolator()

            cl_collapsing_doc_type.startAnimation(anim)

//            cl_collapsing_doc_type.animate()
//                .translationY(cl_collapsing_doc_type.height.toFloat())
//                .alpha(1.0f)
//                .setListener(object : Animator.AnimatorListener{
//                    override fun onAnimationRepeat(animation: Animator?) {
//                    }
//
//                    override fun onAnimationEnd(animation: Animator?) {
//                        open = true
//                    }
//
//                    override fun onAnimationCancel(animation: Animator?) {
//                    }
//
//                    override fun onAnimationStart(animation: Animator?) {
//                    }
//
//                })
        } else {

//            val animate = TranslateAnimation(
//                0f,  // fromXDelta
//                0f,  // toXDelta
//                0f,  // fromYDelta
//                cl_collapsing_doc_type.height.toFloat()
//            ) // toYDelta
//            animate.setAnimationListener(object : Animation.AnimationListener {
//                override fun onAnimationRepeat(animation: Animation?) {
//
//                }
//
//                override fun onAnimationEnd(animation: Animation?) {
////                    cl_collapsing_doc_type.visibility = View.INVISIBLE
//                    open = false
//                }
//
//                override fun onAnimationStart(animation: Animation?) {
//                }
//
//            })
//
//            animate.duration = 500
//            animate.interpolator = ReverseInterpolator()
//            animate.fillAfter = true
//            animate.repeatMode = Animation.REVERSE

//            cl_collapsing_doc_type.startAnimation(animate)

//            val animation: Animation = TranslateAnimation(
//                0f, 0f, 0f, -100f
//            )
//            animation.duration = 250
//            animation.setAnimationListener(object : Animation.AnimationListener {
//                override fun onAnimationRepeat(animation: Animation?) {
//
//                }
//
//                override fun onAnimationEnd(animation: Animation?) {
//                    cl_collapsing_doc_type.visibility = View.VISIBLE
//                }
//
//                override fun onAnimationStart(animation: Animation?) {
//                }
//
//            })

            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_top)
            anim.fillAfter = true
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
//                    cl_collapsing_doc_type.visibility = View.VISIBLE
                    open = true
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            cl_collapsing_doc_type.startAnimation(anim)
//
//            cl_collapsing_doc_type.animate()
//                .translationY(0f)
//                .alpha(0.0f)
//                .setListener(object : Animator.AnimatorListener{
//                    override fun onAnimationRepeat(animation: Animator?) {
//                    }
//
//                    override fun onAnimationEnd(animation: Animator?) {
//                        open = false
//                    }
//
//                    override fun onAnimationCancel(animation: Animator?) {
//                    }
//
//                    override fun onAnimationStart(animation: Animator?) {
//                    }
//
//                })
        }


//        cl_collapsing_doc_type.animate()
//            .translationY(flag)
//            .setDuration(250)
//            .setListener(object : Animator.AnimatorListener {
//                override fun onAnimationRepeat(animation: Animator?) {
//
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//
//                }
//
//                override fun onAnimationStart(animation: Animator?) {
//
//                }
//
//            })
    }

    override fun initViewModel() {
        viewModel.getTabTitle()

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

        btn_expand_category.setOnClickListener {

            viewModel.showDocTypeSlidingView(
                cl_collapsing_doc_type.visibility
            )


//            animate(open)


        }
    }

    fun initAdapter() {


    }
}

class ReverseInterpolator : Interpolator {
    override fun getInterpolation(input: Float): Float {
        return abs(input - 1f)
    }

}
