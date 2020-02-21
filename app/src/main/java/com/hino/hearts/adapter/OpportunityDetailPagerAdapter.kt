package com.hino.hearts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hino.hearts.ui.opportunity.detail.ActivitiesFragment
import com.hino.hearts.ui.opportunity.detail.InformationFragment
import com.hino.hearts.ui.opportunity.detail.OpportunityDetailViewModel

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class OpportunityDetailPagerAdapter(fm : FragmentManager, viewModel: OpportunityDetailViewModel) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mParentViewModel: OpportunityDetailViewModel = viewModel

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InformationFragment()
            else -> ActivitiesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Information"
            else -> "Activities"
        }
    }

    override fun getCount(): Int {
        return 2
    }
}