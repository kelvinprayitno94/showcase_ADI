package com.hino.hearts.ui.pendingtransactions

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hino.hearts.R
import com.hino.hearts.adapter.PendingTransactionAdapter
import com.hino.hearts.databinding.ActivityPendingTransactionsBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_pending_transactions.*
import kotlinx.android.synthetic.main.layout_toolbar_back.*
import kotlinx.android.synthetic.main.layout_toolbar_back.tb_back
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class PendingTransactionsActivity : BaseActivity<ActivityPendingTransactionsBinding>() {

    private val context = this

    private val viewModel by viewModel<PendingTransactionsViewModel>()

    private lateinit var adapter: PendingTransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_pending_transactions)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {

    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        fl_back.onClick {
            finish()
        }
    }

    private fun initLayout() {
        setSupportActionBar(tb_back)

        initNotificationList()
    }

    private fun initNotificationList() {
        adapter = PendingTransactionAdapter()
        adapter.setData(viewModel.pendingTransactionList.value!!)

        rv_pending_transactions.layoutManager = LinearLayoutManager(context)
        rv_pending_transactions.adapter = adapter
    }
}
