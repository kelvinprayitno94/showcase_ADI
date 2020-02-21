package com.hino.hearts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.account.detail.contact.AccountContactFragment
import com.hino.hearts.ui.account.detail.accDetail.AccountDetailFragment
import com.hino.hearts.ui.account.detail.opportunity.AccountOpportunityFragment
import com.hino.hearts.ui.account.detail.vehicles.AccountVehicleFragment

class AccountDetailTabAdapter(fragmentManager: FragmentManager, var titles: List<String>, var data: AccountListResponse.AccListData?) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getPageTitle(position: Int): CharSequence? {

        return titles[position]

    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return AccountDetailFragment(data)
            }

            1 -> {
                return AccountContactFragment()

            }

            2 -> {
                return AccountOpportunityFragment()
            }

            else -> {
                return AccountVehicleFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

}