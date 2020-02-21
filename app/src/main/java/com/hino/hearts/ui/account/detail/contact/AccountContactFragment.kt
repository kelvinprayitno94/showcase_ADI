package com.hino.hearts.ui.account.detail.contact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.adapter.AccountContactAdapter
import com.hino.hearts.databinding.ActivityAccountDetailBinding
import com.hino.hearts.model.AccountContactModel
import com.hino.hearts.ui.BaseFragment
import com.hino.hearts.ui.account.detail.contact.contactDetail.ContactDetailActivity
import com.hino.hearts.ui.account.detail.contact.newContact.NewContactActivity
import kotlinx.android.synthetic.main.fragment_account_detail_contact.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountContactFragment : BaseFragment<ActivityAccountDetailBinding>() {

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
            startActivity(Intent(activity, ContactDetailActivity::class.java))
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

            val item : MutableList<AccountContactModel> = ArrayList()

            for (i in 0 until 6){
                item.add(AccountContactModel("Reinaldo", "08112233xxx"))
            }

            adapter = AccountContactAdapter(it, item, object : AccountContactAdapter.OnAdapterTap{
                override fun onTap(pos: Int) {
                    viewModel.editContactTap()
                }

            })

            rv_contact.adapter = adapter
            rv_contact.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)
        }
    }


}