package com.hino.hearts.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.hino.hearts.R
import com.hino.hearts.adapter.HomeMenuAdapter
import com.hino.hearts.adapter.VisitTargetDialogAdapter
import com.hino.hearts.databinding.FragmentHomeBinding
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.AccountListActivity
import com.hino.hearts.ui.approval.category.ApprovalTabActivity
import com.hino.hearts.ui.event.EventActivity
import com.hino.hearts.ui.opportunity.OpportunityActivity
import com.hino.hearts.util.AlertManager
import com.hino.hearts.util.InterfaceManager
import com.hino.hearts.util.NetworkManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeMenuAdapter.OnMenuTap{

    companion object {
        private const val TWO = 2
        private const val SIX = 6
        private const val EIGHT = 8
        private const val ONE_HUNDRED = 100
    }

    private val viewModel by viewModel<HomeFragmentViewModel>()

    private lateinit var adapter: HomeMenuAdapter
    private lateinit var visitTargetDialogAdapter: VisitTargetDialogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onGetHomeData("custom")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initObserver() {
        viewModel.visitProgressPercentage.observe(viewLifecycleOwner, Observer {
            when (it == ONE_HUNDRED) {
                true -> {
                    cv_home_visit.visibility = View.GONE
                    cv_home_visit_done.visibility = View.VISIBLE
                }
                false -> {
                    cv_home_visit.visibility = View.VISIBLE
                    cv_home_visit_done.visibility = View.GONE

                    tv_home_card_progress_sales.text = getString(
                        R.string.sales_visit_progress,
                        viewModel.visitProgress.value,
                        viewModel.visitProgressTotal.value
                    )
                    home_sales_visit_progress.progress = viewModel.visitProgressPercentage.value!!
                }
            }
        })

        viewModel.approvalRequestCount.observe(viewLifecycleOwner, Observer {
            when(it == "0"){
                true-> {
                    cv_home_approval_request.visibility = View.GONE
                    cv_home_approval_request_done.visibility = View.VISIBLE
                }
                false->{
                    cv_home_approval_request.visibility = View.VISIBLE
                    cv_home_approval_request_done.visibility = View.GONE
                }
            }
        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            (activity as HomeActivity).showLoading()
        })

        viewModel.homeSuccess.observe(viewLifecycleOwner, Observer {
            swipe_refresh_layout.isRefreshing = false
            (activity as HomeActivity).hideLoading()
        })

        viewModel.errorBody.observe(viewLifecycleOwner, Observer {
            swipe_refresh_layout.isRefreshing = false
            (activity as HomeActivity).hideLoading()

            NetworkManager.getInstance().handleResponse(context, it)
        })

        viewModel.responseError.observe(viewLifecycleOwner, Observer {
            swipe_refresh_layout.isRefreshing = false
            (activity as HomeActivity).hideLoading()

            NetworkManager.getInstance().handleErrorResponse(context, it)
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        cv_home_visit.onClick {
            showVisitTargetDialog()
        }

        cv_home_visit_done.onClick {
            showVisitTargetDialog()
        }

        cv_home_approval_request.onClick {
            toast("Approval card clicked")
        }

        cv_home_approval_request_done.onClick {
            toast("Approval card clicked")
        }

        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = true

            viewModel.onGetHomeData("refresh")
        }
    }

    private fun initLayout() {
        when (viewModel.role.value == "Sales") {
            true -> {
                tv_home_left_info_desc.text = getString(R.string.home_left_info_sales)
                tv_home_right_info_desc.text = getString(R.string.home_right_info_sales)

                cv_home_approval_request.visibility = View.GONE
                cv_home_approval_request_done.visibility = View.GONE
            }
            false -> {
                tv_home_left_info_desc.text = getString(R.string.home_left_info_nonsales)
                tv_home_right_info_desc.text = getString(R.string.home_right_info_nonsales)

                cv_home_visit.visibility = View.GONE
                cv_home_visit_done.visibility = View.GONE
            }
        }

        setHomeMenu()
    }

    private fun setHomeMenu() {
        adapter = HomeMenuAdapter(this)
        adapter.setData(viewModel.homeMenuList.value!!)

        var spanCount = 0
        when (context?.let { InterfaceManager.getInstance().isTablet(it) }) {
            true -> {
                spanCount = EIGHT
            }
            false -> {
                spanCount = SIX
            }
        }

        val gridLayoutManager = GridLayoutManager(activity, spanCount)
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                return TWO
            }
        }
        rv_home_menu.layoutManager = gridLayoutManager
        rv_home_menu.adapter = adapter
    }

    private fun showVisitTargetDialog() {
        visitTargetDialogAdapter = VisitTargetDialogAdapter()
        visitTargetDialogAdapter.setData(viewModel.visitTargetList.value!!)

        AlertManager.getInstance().showVisitTargetDialog(
            context,
            viewModel.todayDate.value,
            DialogInterface.OnClickListener { dialog, i ->
                dialog.dismiss()
                (activity as HomeActivity).showAddVisitButton()
            },
            visitTargetDialogAdapter
        )
    }

    override fun onTap(menu: Int) {
        when (menu) {
            R.string.catalogues -> {
                Toast.makeText(activity, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
            R.string.accounts -> {
                startActivity<AccountListActivity>()
            }
            R.string.spare_part -> {
                Toast.makeText(activity, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
            R.string.events -> {
                startActivity<EventActivity>()
            }
            R.string.approvals -> {
                startActivity<ApprovalTabActivity>()
            }
            R.string.opportunities -> {
                startActivity<OpportunityActivity>()
            }
        }
    }
}
