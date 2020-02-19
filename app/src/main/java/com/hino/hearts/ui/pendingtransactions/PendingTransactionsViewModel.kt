package com.hino.hearts.ui.pendingtransactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.PendingTransaction
import com.hino.hearts.util.InterfaceManager
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class PendingTransactionsViewModel : ViewModel() {

    var pendingTransactionList: MutableLiveData<ArrayList<PendingTransaction>> = MutableLiveData()

    init {
        pendingTransactionList.value = initNotificationList()
    }

    private fun initNotificationList(): ArrayList<PendingTransaction> {
        val pendingTransactionList: ArrayList<PendingTransaction> = ArrayList()

        val current = Calendar.getInstance().time
        val date = InterfaceManager.getInstance().convertStringFromDate(current)

        pendingTransactionList.add(
            PendingTransaction(
                1,
                "Pending Transaction Title",
                date
            )
        )
        pendingTransactionList.add(
            PendingTransaction(
                2,
                "Pending Transaction Title",
                date
            )
        )
        pendingTransactionList.add(
            PendingTransaction(
                3,
                "Pending Transaction Title",
                date
            )
        )
        pendingTransactionList.add(
            PendingTransaction(
                4,
                "Pending Transaction Title",
                date
            )
        )
        pendingTransactionList.add(
            PendingTransaction(
                5,
                "Pending Transaction Title",
                date
            )
        )

        return pendingTransactionList
    }
}