package com.hino.hearts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hino.hearts.ui.opportunity.detail.activities.ActivitiesFragment
import com.hino.hearts.ui.opportunity.detail.information.InformationFragment

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class OpportunityDetailPagerAdapter(fm : FragmentManager, opportunityId: Int) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val informationFragment: InformationFragment =
        InformationFragment(
            opportunityId
        )
    private val activitiesFragment: ActivitiesFragment =
        ActivitiesFragment(
            opportunityId
        )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> informationFragment
            else -> activitiesFragment
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

    fun refreshActivity() {
        activitiesFragment.refresh()
    }
}