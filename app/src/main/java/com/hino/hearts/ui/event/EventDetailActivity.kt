package com.hino.hearts.ui.event

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.hino.hearts.R
import com.hino.hearts.adapter.EventDetailPagerAdapter
import com.hino.hearts.databinding.ActivityEventDetailBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_event.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailActivity : BaseActivity<ActivityEventDetailBinding>() {

    companion object {
        private const val TWO_THOUSANDS: Long = 2000
        private var id = 0

    }

    private val context = this

    private val viewModel by viewModel<EventDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_event_detail)

        initObserver()
        initViewModel()
        initEvent()
        initData()
        initLayout()
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        fl_back.onClick {
            onBackPressed()
        }
    }

    private fun initData() {
        id = intent.getIntExtra("id", 0)
    }

    private fun initLayout() {
        initScrollableBehavior()
        initTabLayout()
    }

    private fun initTabLayout() {
        vp_event_detail.adapter = EventDetailPagerAdapter(supportFragmentManager)
        vp_event_detail.offscreenPageLimit = 2
        tl_event_detail.setIndicatorWidth(16)
        tl_event_detail.setupWithViewPager(vp_event_detail)
    }

    private fun initScrollableBehavior() {
        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { _, offset ->
            if (offset < -200) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    iv_back.colorFilter =
                        BlendModeColorFilter(getColor(R.color.red), BlendMode.SRC_ATOP)
                    iv_add_attendee.colorFilter =
                        BlendModeColorFilter(getColor(R.color.red), BlendMode.SRC_ATOP)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        iv_back.setColorFilter(getColor(R.color.red), PorterDuff.Mode.SRC_ATOP)
                        iv_add_attendee.setColorFilter(
                            getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP
                        )
                    } else {
                        iv_back.setColorFilter(
                            resources.getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP
                        )
                        iv_add_attendee.setColorFilter(
                            resources.getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP
                        )
                    }
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    iv_back.colorFilter =
                        BlendModeColorFilter(getColor(R.color.white), BlendMode.SRC_ATOP)
                    iv_add_attendee.colorFilter =
                        BlendModeColorFilter(getColor(R.color.white), BlendMode.SRC_ATOP)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        iv_back.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
                        iv_add_attendee.setColorFilter(
                            getColor(R.color.white),
                            PorterDuff.Mode.SRC_ATOP
                        )
                    } else {
                        iv_back.setColorFilter(
                            resources.getColor(R.color.white),
                            PorterDuff.Mode.SRC_ATOP
                        )
                        iv_add_attendee.setColorFilter(
                            resources.getColor(R.color.white),
                            PorterDuff.Mode.SRC_ATOP
                        )
                    }
                }
            }
        })
    }
}
