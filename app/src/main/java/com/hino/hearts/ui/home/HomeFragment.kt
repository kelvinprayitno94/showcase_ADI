package com.hino.hearts.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.hino.hearts.R
import com.hino.hearts.adapter.HomeMenuAdapter
import com.hino.hearts.databinding.FragmentHomeBinding
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.util.InterfaceManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        private const val TWO = 2
        private const val SIX = 6
        private const val EIGHT = 8
        private const val TEN = 10
        private const val ONE_HUNDRED = 100
    }

    private val viewModel by viewModel<HomeFragmentViewModel>()
    private lateinit var adapter: HomeMenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initObserver() {
        viewModel.visitProgressPercentage.observe(viewLifecycleOwner, Observer {
            when (it == ONE_HUNDRED) {
                true -> {
                    cv_home_visit.visibility = View.INVISIBLE
                    cv_home_visit_done.visibility = View.VISIBLE
                }
                false -> {
                    cv_home_visit.visibility = View.VISIBLE
                    cv_home_visit_done.visibility = View.INVISIBLE

                    tv_home_card_progress_sales.text = getString(
                        R.string.sales_visit_progress,
                        viewModel.visitProgress.value,
                        viewModel.visitProgressTotal.value
                    )
                    home_sales_visit_progress.progress = viewModel.visitProgressPercentage.value!!
                }
            }
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        iv_add_visit_progress.onClick {
            viewModel.addVisitProgress()
        }
    }

    private fun initLayout() {
        when (viewModel.role.value == "Sales") {
            true -> {
                tv_home_left_info_desc.text = getString(R.string.home_left_info_sales)
                tv_home_right_info_desc.text = getString(R.string.home_right_info_sales)

                cv_home_record_visit.visibility = View.VISIBLE
                cv_home_approval_request.visibility = View.INVISIBLE
            }
            false -> {
                tv_home_left_info_desc.text = getString(R.string.home_left_info_nonsales)
                tv_home_right_info_desc.text = getString(R.string.home_right_info_nonsales)

                cv_home_record_visit.visibility = View.INVISIBLE
                cv_home_approval_request.visibility = View.VISIBLE
            }
        }

        setHomeMenu()
    }

    private fun setHomeMenu() {
        adapter = HomeMenuAdapter()
        adapter.setData(viewModel.homeMenuList.value!!)

        var spanCount = 0
        when (context?.let { InterfaceManager.getInstance().isTablet(it) }) {
            true -> {
                spanCount = when (viewModel.role.value == "Sales") {
                    true -> {
                        EIGHT
                    }
                    false -> {
                        TEN
                    }
                }
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
}
