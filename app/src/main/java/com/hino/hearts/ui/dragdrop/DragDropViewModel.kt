package com.hino.hearts.ui.dragdrop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.model.OpportunityModel
import kotlin.random.Random

class DragDropViewModel : ViewModel() {
    var headers: MutableList<String> = ArrayList()
    var data: HashMap<String, MutableList<OpportunityModel.OpportunityData>> = HashMap()
    var backClicked: MutableLiveData<Boolean> = MutableLiveData()

    init {
        headers.add("Quotation")
        headers.add("Negotiation")
        headers.add("Approved")

        val size: Int = headers.size
        var index = 1
        for (i in 0 until size) {
            val items: MutableList<OpportunityModel.OpportunityData> = ArrayList()
            val itemSize: Int = Random.nextInt(8)
            for (j in 1..itemSize) {
                val item = OpportunityModel.OpportunityData(index, "Text $index", "PT Dihardja Software", index * 100000000L)
                items.add(item)
                index++
            }

            val header = headers[i]
            data[header] = items
        }
    }

    fun onBackPressed() {
        backClicked.value = true
    }
}