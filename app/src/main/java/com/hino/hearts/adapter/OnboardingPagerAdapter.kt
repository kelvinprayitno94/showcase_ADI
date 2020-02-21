package com.hino.hearts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hino.hearts.ui.onboarding.first.OnboardingFirstFragment
import com.hino.hearts.ui.onboarding.second.OnboardingSecondFragment
import com.hino.hearts.ui.onboarding.third.OnboardingThirdFragment

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class OnboardingPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return OnboardingFirstFragment()
            1 -> return OnboardingSecondFragment()
            2 -> return OnboardingThirdFragment()
        }
        return OnboardingThirdFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}