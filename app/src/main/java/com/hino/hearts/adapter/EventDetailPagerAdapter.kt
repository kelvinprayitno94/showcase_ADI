package com.hino.hearts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hino.hearts.ui.event.attendees.AttendeesFragment
import com.hino.hearts.ui.event.eventinformation.EventInformationFragment
import com.hino.hearts.util.ConstantManager

/**
 * Created by Dihardja Software on 2020-02-20.
 */
class EventDetailPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return EventInformationFragment()
            1 -> return AttendeesFragment()
        }
        return EventInformationFragment()
    }

    override fun getPageTitle(i: Int): CharSequence? {
        when (i) {
            0 -> return ConstantManager.EVENT_INFORMATION
            1 -> return ConstantManager.ATTENDEES
        }
        return ConstantManager.EVENT_INFORMATION
    }

    override fun getCount(): Int {
        return 2
    }
}