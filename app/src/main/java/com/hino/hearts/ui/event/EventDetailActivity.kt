package com.hino.hearts.ui.event

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.hino.hearts.R
import com.hino.hearts.adapter.EventDetailPagerAdapter
import com.hino.hearts.databinding.ActivityEventDetailBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.util.ConstantManager
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.layout_toolbar_event.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailActivity : BaseActivity<ActivityEventDetailBinding>() {

    companion object {
        private const val TWO = 2
        private const val THREE = 3
        private const val SIXTEEN = 16
        private const val TWO_HUNDREDS = 200
    }

    private val context = this

    private val viewModel by viewModel<EventDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_event_detail)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel

        viewModel.eventId.value = intent.getIntExtra(ConstantManager.EVENT_ID, 0)
        viewModel.initEvent()
    }

    override fun initEvent() {
        fl_back.onClick {
            onBackPressed()
        }
    }

    private fun initLayout() {
        initScrollableBehavior()
        initTabLayout()

        layout_custom_loading.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            initDescription()

            Log.d("lalala", viewModel.event.toString())
            when (viewModel.eventId.value) {
                1 -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.eventbanner_placeholder)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
                2 -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.event_banner1)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
                3 -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.event_banner2)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
                4 -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.event_banner3)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
                5 -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.event_banner4)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
                else -> {
                    Glide.with(context)
//                    .load(BuildConfig.IMAGE_URL+event.imgUrl)
                        .load(R.drawable.event_placeholder)
                        .placeholder(R.drawable.eventbanner_placeholder).into(iv_event_detail)
                }
            }

            layout_custom_loading.visibility = View.GONE
        }, 500)
    }

    private fun initTabLayout() {
        vp_event_detail.adapter = EventDetailPagerAdapter(supportFragmentManager)
        vp_event_detail.offscreenPageLimit = TWO
        tl_event_detail.setIndicatorWidth(SIXTEEN)
        tl_event_detail.setupWithViewPager(vp_event_detail)
    }

    private fun initDescription() {
        when (tv_event_detail_desc.lineCount > THREE) {
            true -> {
                tv_event_detail_desc.ellipsize = TextUtils.TruncateAt.END

                viewModel.showButtonText.value = getString(R.string.show_more)
                btn_show_more.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                btn_show_more.visibility = View.VISIBLE

                btn_show_more.onClick {
                    when (viewModel.showButtonText.value) {
                        getString(R.string.show_more) -> {
                            tv_event_detail_desc.maxLines = Int.MAX_VALUE
                            viewModel.showButtonText.value = getString(R.string.show_less)
                        }
                        getString(R.string.show_less) -> {
                            tv_event_detail_desc.maxLines = THREE
                            viewModel.showButtonText.value = getString(R.string.show_more)
                        }
                    }
                }
            }
            false -> {
                btn_show_more.visibility = View.INVISIBLE
            }
        }
    }


    private fun initScrollableBehavior() {
        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { _, offset ->
            if (offset < -TWO_HUNDREDS) {
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
