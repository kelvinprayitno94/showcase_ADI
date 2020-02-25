package com.hino.hearts.ui.opportunity.detail

import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by Dihardja Software on 2020-02-18.
 */
class InformationViewModel : ViewModel() {
    var opportunityId: Int = 0
    var accountName: String? = "Account Name not Found"
    var opportunityValue: Long? = 0

    private val mFormatter: NumberFormat = DecimalFormat("#,###")

    fun formattedBudget() : String {
        return "Rp${mFormatter.format(opportunityValue)}"
    }
}