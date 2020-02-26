package com.hino.hearts.ui.account.detail.contact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountContactAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.model.AccountContactModel
import com.hino.hearts.network.response.account.AccountListResponse
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.contact.contactDetail.ContactDetailActivity
import com.hino.hearts.ui.account.detail.contact.newContact.NewContactActivity
import com.hino.hearts.util.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_account_detail_contact.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountContactFragment(var data: List<AccountListResponse.ContactData>?) : BaseFragment<ActivityAccountDetailBinding>() {

    private val viewModel by viewModel<AccountContactViewModel>()

    lateinit var adapter:AccountContactAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_detail_contact
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initViewModel()
        initEvent()
        initAdapter()
    }

    override fun initObserver() {
        viewModel.navEditContactLiveData.observe(viewLifecycleOwner, Observer {
            startActivity<ContactDetailActivity>(
                "contact_data" to it
            )
        })

        viewModel.navNewContactLiveData.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(activity, NewContactActivity::class.java))
        })
    }

    override fun initViewModel() {

    }

    override fun initEvent() {
        btn_detail_contact_add.setOnClickListener {
            viewModel.addContactTap()
        }
    }

    private fun initAdapter() {
        context?.let {

            adapter = AccountContactAdapter(it, data, object : AccountContactAdapter.OnAdapterTap{
                override fun onTap(pos: Int) {
                    viewModel.editContactTap(data?.get(pos))
                }

            })

            rv_contact.adapter = adapter
            rv_contact.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
            rv_contact.addItemDecoration(DividerItemDecoration(ContextCompat.getDrawable(context!!, R.drawable.transparant_divider)))
        }
    }


}