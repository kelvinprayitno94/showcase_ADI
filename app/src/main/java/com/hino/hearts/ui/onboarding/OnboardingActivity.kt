package com.hino.hearts.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.hino.hearts.R
import com.hino.hearts.adapter.OnboardingPagerAdapter
import com.hino.hearts.databinding.ActivityOnboardingBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {

    private val viewModel by viewModel<OnboardingViewModel>()
    private var adapter: OnboardingPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_onboarding)

        initViewModel()
        initLayout()
        initEvent()
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        btn_start.onClick {
            finish()
            startActivity<LoginActivity>()
            overridePendingTransition(0, 0)
        }

        vp_onboarding.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position == 2) {
                    true -> {
                        btn_start.visibility = View.VISIBLE
                    }
                    false -> {
                        btn_start.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun initLayout() {
        adapter = OnboardingPagerAdapter(supportFragmentManager)
        vp_onboarding.adapter = adapter
        di_onboarding.setViewPager(vp_onboarding)
    }
}
