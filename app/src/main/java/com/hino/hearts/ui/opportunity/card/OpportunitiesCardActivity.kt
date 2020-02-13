package com.hino.hearts.ui.opportunity.card

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.hino.hearts.model.OpportunityModel
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityOpportunitiesCardBinding
import com.hino.hearts.model.CardViewModel
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_opportunities_card.*


class OpportunitiesCardActivity : BaseActivity<ActivityOpportunitiesCardBinding>() {

    var item: MutableList<Pair<String, OpportunityModel>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_opportunities_card)

        for (i in 0 until 6) {

            var stringList: MutableList<CardViewModel> = ArrayList()

            val count = if (i % 2 == 0) 5 else 9

            for (_i in 0 until count) {
                stringList.add(CardViewModel("${i}", "${_i}", "Card Title ${i} ${_i}"))
            }

            item.add(Pair("10${i}", OpportunityModel("10${i}", "Parent Title", stringList)))
        }

        generateCard()


    }

    fun generateCard() {
        for (i in item.indices) {

            val childList = item[i].second.cardList

            val scrollView = ScrollView(this@OpportunitiesCardActivity)
            scrollView.setBackgroundColor(resources.getColor(android.R.color.holo_orange_dark))

            val viewGroupParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            viewGroupParams.setMargins(30)

            scrollView.layoutParams = viewGroupParams

            scrollView.setPadding(5)
            scrollView.tag = item[i].first

            childList?.let {

                val linearLayout = LinearLayout(this)

                val title = TextView(this@OpportunitiesCardActivity)
                title.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                title.setPadding(0, 0, 0, 10)
                title.textSize = 18f
                title.setTextColor(resources.getColor(android.R.color.black))

                title.text = "Opportunity"

                linearLayout.addView(title)

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                layoutParams.setMargins(20)
                linearLayout.orientation = LinearLayout.VERTICAL
                linearLayout.layoutParams = layoutParams
                linearLayout.setPadding(10)
                linearLayout.tag = i

                for (_i in it.indices) {
                    val cardView = CardView(this@OpportunitiesCardActivity)
                    val cardLayParam = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    cardLayParam.setMargins(5)
                    cardView.layoutParams = cardLayParam
                    cardView.setCardBackgroundColor(resources.getColor(android.R.color.white))
                    val cardTitle = TextView(this@OpportunitiesCardActivity)
                    val cardAccount = TextView(this@OpportunitiesCardActivity)
                    val cardValue = TextView(this@OpportunitiesCardActivity)
                    val layoutParam = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    cardTitle.layoutParams = layoutParam
                    cardAccount.layoutParams = layoutParam
                    cardValue.layoutParams = layoutParam
                    cardTitle.setPadding(10)
                    cardTitle.textSize = 18f
                    cardTitle.setTextColor(resources.getColor(android.R.color.black))
//                    cardView.tag = Pair(it[_i].parentIndex, it[_i].childIndex)
                    cardView.tag = it[_i]

                    cardTitle.text = "Opportunity Title"
                    cardAccount.text = "Account Name"
                    cardValue.text = "Opportunity Value"

                    cardView.addView(cardTitle)
                    cardView.addView(cardAccount)
                    cardView.addView(cardValue)

                    setDrag(cardView)

                    linearLayout.addView(cardView)

                }

                Log.d(
                    "generate cardview",
                    "layout count: ${linearLayout.childCount}"
                )



                scrollView.addView(linearLayout)
            }

            ll_parent.addView(scrollView)
        }
    }


    fun setDrag(cardView: CardView) {
        cardView.setOnLongClickListener {
            val item = ClipData.Item(it.tag as? CharSequence)

            val dragData = ClipData(
                it.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
            )

            val myShadow = View.DragShadowBuilder(it)
//                    MyDragShadowBuilder(it)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(
                    dragData,   // the data to be dragged
                    myShadow,   // the drag shadow builder
                    it,       // no need to use local data
                    0           // flags (not currently used, set to 0)
                )
            } else {
                it.startDrag(
                    dragData,   // the data to be dragged
                    myShadow,   // the drag shadow builder
                    it,       // no need to use local data
                    0           // flags (not currently used, set to 0)
                )
            }

            return@setOnLongClickListener false
        }

        cardView.setOnDragListener { v, event ->

            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return@setOnDragListener true
                    }
                    return@setOnDragListener false
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
//                    v.background.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)

//                    v.invalidate()
                    return@setOnDragListener true
                }

                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@setOnDragListener true
                }

                DragEvent.ACTION_DRAG_EXITED -> {
//                    v.background.clearColorFilter()
//                    v.invalidate()
                    return@setOnDragListener true
                }

                DragEvent.ACTION_DROP -> {

                    // Turns off any color tints
//                    v.background.clearColorFilter()
                    // Invalidates the view to force a redraw
//                    v.invalidate()

                    if (event.localState != null) {

                        val container = v as CardView

                        val target = event.localState as View

                        var conViewgroup = container.parent as ViewGroup
                        var tarViewgroup = target.parent as ViewGroup

                        val conIndex = conViewgroup.indexOfChild(container)
                        val tarIndex = tarViewgroup.indexOfChild(target)

                        Log.d(
                            "ids",
                            "container view tag: ${container.tag}, target tag ${target.tag}"
                        )

                        var containerPair = container.tag as CardViewModel
                        val targetPair = target.tag as CardViewModel

//                        if (conViewgroup.tag == tarViewgroup.tag) {
                        item[conViewgroup.tag as Int].second.cardList?.set(
                            conIndex,
                            targetPair
                        )
                        item[tarViewgroup.tag as Int].second.cardList?.set(
                            tarIndex,
                            containerPair
                        )
//                        } else {
//
//                            item[conViewgroup.tag as Int].second.cardList?.add(conIndex, targetPair)
//                            item[tarViewgroup.tag as Int].second.cardList?.remove(targetPair)
//
////                            var isInsert = false
////                            var isRemoved = false
////                            var addIndex = -1
////                            var addChildIndex = -1
////
////                            for (pair in 0 until item.size) {
////                                if (item[pair].first == containerPair.parentIndex && !isInsert) {
////
////                                    for (childpair in 0 until item[pair].second.cardList?.size!!) {
////                                        if (item[pair].second.cardList?.get(childpair)?.childIndex == containerPair.childIndex) {
////                                            addIndex = pair
////                                            addChildIndex = childpair
////                                            isInsert = true
////                                        }
////                                    }
////                                }
////
////                                if (isInsert) {
////                                    break
////                                }
////
////                            }
////
////                            for (pair in 0 until item.size) {
////
////                                if (item[pair].first == targetPair.parentIndex && !isRemoved) {
////                                    item[pair].second.cardList?.remove(targetPair)
////                                    isRemoved = true
////                                }
////
////                                if (isRemoved) {
////                                    break
////                                }
////
////                            }
////
////                            if (addIndex >= 0 && addChildIndex >= 0)
////                                item[addIndex].second.cardList?.add(addChildIndex, targetPair)
//
//                        }

                        ll_parent.removeAllViews()
                        generateCard()


                    } else Log.d("ids", "parent view tag: null")



                    return@setOnDragListener true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    // Turns off any color tinting
                    v.background.clearColorFilter()
                    // Invalidates the view to force a redraw
                    v.invalidate()
                    // Does a getResult(), and displays what happened.
                    if (event.result)
//                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show()
                    else
//                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show()
                    // returns true; the value is ignored.
                        return@setOnDragListener true
                }
            }

            return@setOnDragListener false
        }
    }

    override fun initObserver() {

    }

    override fun initViewModel() {

    }

    override fun initEvent() {

    }


}