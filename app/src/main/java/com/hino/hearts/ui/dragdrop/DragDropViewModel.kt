package com.hino.hearts.ui.dragdrop

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DragDropViewModel : ViewModel() {
    var headers: MutableList<String> = ArrayList()
    var data: HashMap<String, MutableList<String>> = HashMap()

    init {
        val size: Int = Random.nextInt(4) + 3
        var index: Int = 1
        for (i in 1..size) {
            val header = "RecyclerView $i"
            headers.add(header)

            val items: MutableList<String> = ArrayList()
            val itemSize: Int = Random.nextInt(8)
            for (j in 1..itemSize) {
                items.add("Text $index")
                index++
            }
            data[header] = items
        }
    }
}